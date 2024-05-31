package io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.api.atproto.repo.GetRecord
import com.milkcocoa.info.milkyway.atproto.action.Action
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AnyRecord
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.aturi.NSID
import com.milkcocoa.info.milkyway.models.aturi.RecordKey
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.models.atproto.sync.StreamHeaderPattern
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.models.atproto.sync.defs.Commit
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.models.atproto.sync.defs.RepoOperation
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamClosedCallback
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamErrorCallback
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamOpenedCallback
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamReceiveCallback
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import kotlinx.serialization.modules.polymorphic

abstract class StreamRequest(
    val domain: Domain,
    val action: Action,
    val collectionFilter: Set<NSID>,
    val milkyway: Milkyway
) {
    private var client: HttpClient? = null

    private var openedCallback: StreamOpenedCallback? = null
    private var closedCallback: StreamClosedCallback? = null
    private var receiveCallback: StreamReceiveCallback? = null
    private var errorCallback: StreamErrorCallback? = null

    fun openCallback(callback: StreamOpenedCallback?) = apply { openedCallback = callback }

    fun closedCallback(callback: StreamClosedCallback?) = apply { closedCallback = callback }

    fun receiveCallback(callback: StreamReceiveCallback?) = apply { receiveCallback = callback }

    fun errorCallback(callback: StreamErrorCallback?) = apply { errorCallback = callback }

    private var _isConnected = false
    public val isConnected: Boolean get() = _isConnected

    suspend fun DefaultClientWebSocketSession.incomingMessage() {
        while (true) {
            for (message in incoming) {
                when (message) {
                    is Frame.Text -> {
                        // これは仕様上ない
                    }
                    is Frame.Binary -> {
                        onMessage(message.readBytes())
                    }
                    is Frame.Ping -> {
                        send(Frame.Pong(message.readBytes()))
                    }
                    is Frame.Pong -> {}
                    is Frame.Close -> {
                        _isConnected = false
                        closedCallback?.onStreamClose()
                    }
                }
            }
        }
    }

    suspend fun open() {
        if (client != null) {
            client!!.close()
            _isConnected = false
            client = null
        }

        _isConnected = false

        client =
            kotlin.runCatching {
                HttpClient(CIO) {
                    install(WebSockets) {
                        // bluesky api does not response 'PONG' frame???
                        // pingInterval = 20_000
                    }
                }.apply {
                    webSocket(
                        urlString = "${domain.asWss}/xrpc/${action.action}",
                        request = {}
                    ) {
                        kotlin.runCatching {
                            incomingMessage()
                        }.getOrElse {
                            errorCallback?.onStreamError(it)
                            closedCallback?.onStreamClose()
                            close()
                        }
                    }
                }
            }.getOrElse {
                errorCallback?.onStreamError(it)
                _isConnected = false
                null
            }
    }

    suspend fun close() {
        kotlin.runCatching {
            client?.close()
            client = null
            _isConnected = false
        }.getOrElse { _isConnected = false }
    }

    @OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
    private suspend fun onMessage(data: ByteArray) {
        try {
            val cbor =
                Cbor {
                    ignoreUnknownKeys = true

                    serializersModule +=
                        SerializersModule {
                            polymorphic(Record::class) {
                                defaultDeserializer { AnyRecord.serializer() }
                            }
                        }
                    KtorHttpClient.getSerializersModules()
                        .takeIf { it.isNotEmpty() }
                        ?.let { serializerModules ->
                            serializerModules.reduce { a, b -> a + b }
                        }?.run {
                            serializersModule += this
                        }
                }
            val d = data.toHexString()

            // 例えば
            //   {"t": "#commit", "op": 1}
            // のようなヘッダがあったとき、
            // Blueskyは
            // A2                   # map(2)
            //   61                # text(1)
            //      74             # "t"
            //   67                # text(7)
            //      23636F6D6D6974 # "#commit"
            //   62                # text(2)
            //      6F70           # "op"
            //   01                # unsigned(1)
            // という形式で返してくる。
            // 対して、kotlinx-serialization-cborはmapの長さを明記しないため,
            // BF                   # map(*)
            //   61                # text(1)
            //      74             # "t"
            //   67                # text(7)
            //      23636F6D6D6974 # "#commit"
            //   62                # text(2)
            //      6F70           # "op"
            //   01                # unsigned(1)
            //   FF                # primitive(*)
            // というバイナリに出力する。
            // そのため、その場でヘッダーのモデルをエンコードして探し出す、というのはできない。
            // が、https://atproto.com/specs/event-stream#framing により、その形式がわかるため、
            // lexiconと合わせて既知の形式をすべて持っておけば、効率的にパースすることが可能。
            StreamHeaderPattern.entries.forEach PredictCBORHeader@{ header ->
                d.substringAfter(
                    delimiter = header.hexString,
                    missingDelimiterValue = ""
                ).takeIf { it.isNotEmpty() }?.let {
                    when (header) {
                        StreamHeaderPattern.COMMIT -> {
                            val commit: Commit = cbor.decodeFromHexString(it)
                            commit.ops.forEach OpIteration@{ op ->
                                val (collection, rkey) =
                                    op.path.split(
                                        delimiters = arrayOf("/"),
                                        limit = 2
                                    ).takeIf { it.size == 2 }?.let {
                                        NSID(it[0]) to RecordKey(it[1])
                                    } ?: return@OpIteration

                                if (collectionFilter.isNotEmpty() && collectionFilter.contains(collection).not()) {
                                    return@OpIteration
                                }

                                when (op.action) {
                                    RepoOperation.OperationAction.Create -> {
                                        val record =
                                            milkyway.atProtocol()
                                                .repo()
                                                .getRecord(
                                                    GetRecord.GetRecordRequest(
                                                        repo = commit.repo.value,
                                                        collection = collection,
                                                        rkey = rkey
                                                    )
                                                )
                                        receiveCallback?.onStreamReceive(
                                            cid = record.cid,
                                            uri = record.uri,
                                            record = record.value
                                        )
                                    }
                                    RepoOperation.OperationAction.Update -> {
                                    }
                                    RepoOperation.OperationAction.Delete -> {
                                    }
                                }
                            }
                        }
                        StreamHeaderPattern.HANDLE -> {
                        }
                        StreamHeaderPattern.INFO -> {
                        }
                        StreamHeaderPattern.MIGRATE -> {
                        }
                        StreamHeaderPattern.IDENTITY -> {
                        }
                        StreamHeaderPattern.TOMBSTONE -> {
                        }
                        StreamHeaderPattern.ERROR -> {
                        }
                    }
                    return@PredictCBORHeader
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}