package io.github.milkcocoa0902

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.action.Action
import com.milkcocoa.info.milkyway.api.installBskyDependencies
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.domain.OfficialDomain
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.StreamRequest
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamClosedCallback
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamErrorCallback
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamOpenedCallback
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback.StreamReceiveCallback
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
suspend fun main() {
    GlobalScope.launch {
        Milkyway.installBskyDependencies()
        object : StreamRequest(
            domain = Domain("bsky.network"),
            action = Action("com.atproto.sync.subscribeRepos"),
            collectionFilter = setOf(),
            milkyway = Milkyway.instance(OfficialDomain.BskySocial)
        ) {}.openCallback(
            object : StreamOpenedCallback {
                override fun onStreamOpen() {
                }
            }
        ).closedCallback(
            object : StreamClosedCallback {
                override fun onStreamClose() {
                }
            }
        ).receiveCallback(
            object : StreamReceiveCallback {
                override fun onStreamReceive(
                    cid: String?,
                    uri: AtUri,
                    record: Record<*>
                ) {
                    println(record)
                }
            }
        ).errorCallback(
            object : StreamErrorCallback {
                override fun onStreamError(e: Throwable) {
                    println(e)
                }
            }
        ).open()
    }.join()
}