package io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.aturi.AtUri

interface StreamReceiveCallback {
    fun onStreamReceive(
        cid: String?,
        uri: AtUri,
        record: Record<*>
    )
}