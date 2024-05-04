package io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream

import com.milkcocoa.info.milkyway.models.AnyRecord
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamClosedCallback
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamErrorCallback
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamOpenedCallback
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamReceiveCallback
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import kotlinx.serialization.modules.polymorphic

class StreamClient {
    val client =
        HttpClient(CIO) {
            install(WebSockets) {
                pingInterval = 20_000
            }
        }

    private var openedCallback: StreamOpenedCallback? = null
    private var closedCallback: StreamClosedCallback? = null
    private var receiveCallback: StreamReceiveCallback? = null
    private var errorCallback: StreamErrorCallback? = null

    fun openCallback(callback: StreamOpenedCallback?) = apply { openedCallback = callback }

    fun closedCallback(callback: StreamClosedCallback?) = apply { closedCallback = callback }

    fun receiveCallback(callback: StreamReceiveCallback?) = apply { receiveCallback = callback }

    fun errorCallback(callback: StreamErrorCallback?) = apply { errorCallback = callback }

    init {
    }

    private var _isConnected = false
    public val isConnected: Boolean get() = _isConnected

    suspend fun DefaultClientWebSocketSession.outputMessages() {
        try {
            for (message in incoming) {
                when (message) {
                    is Frame.Text -> {
                        // これは仕様上ない
                    }
                    is Frame.Binary -> {
                        onMessage(message.readBytes())
                    }
                    is Frame.Ping,
                    is Frame.Pong -> {
                    }
                    is Frame.Close -> {
                        _isConnected = false
                        closedCallback?.onStreamClose()
                    }
                }
                message as? Frame.Text ?: continue
                println(message.readText())
            }
        } catch (e: Exception) {
            errorCallback?.onStreamError(e)
            _isConnected = false
        }
    }

    suspend fun open() {
        kotlin.runCatching {
            client.close()
        }.getOrNull()
        _isConnected = false

        kotlin.runCatching {
            client.webSocket(
                method = HttpMethod.Get,
                host = "",
                port = 443,
                path = ""
            ) {
                val messageOutputRoutine = launch { outputMessages() }
                messageOutputRoutine.cancelAndJoin()
            }
        }.getOrElse { _isConnected = false }
    }

    suspend fun close() {
        kotlin.runCatching {
            client.close()
            _isConnected = false
        }.getOrElse { _isConnected = false }
    }

    @OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
    private fun onMessage(data: ByteArray) {
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
    }
}