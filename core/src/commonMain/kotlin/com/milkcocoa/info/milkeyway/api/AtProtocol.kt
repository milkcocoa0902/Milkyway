package com.milkcocoa.info.milkeyway.api

import com.milkcocoa.info.milkeyway.api.com.atproto.server.Server
import com.milkcocoa.info.milkeyway.domain.Domain

class AtProtocol(private val domain: Domain) {
    fun server() = Server(domain)
}