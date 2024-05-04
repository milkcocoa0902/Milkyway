package io.github.milkcocoa0902.com.milkcocoa.info.milkyway.stream.callback

import com.milkcocoa.info.milkyway.models.Record

interface StreamReceiveCallback {
    fun onStreamReceive(
        cid: String?,
        uri: String,
        record: Record<*>
    )
}