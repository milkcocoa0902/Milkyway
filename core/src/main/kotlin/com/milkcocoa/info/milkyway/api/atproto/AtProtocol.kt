package com.milkcocoa.info.milkyway.api.atproto

import com.milkcocoa.info.milkyway.api.atproto.repo.Repo
import com.milkcocoa.info.milkyway.api.atproto.server.Server
import com.milkcocoa.info.milkyway.domain.Domain

class AtProtocol(private val domain: Domain) {
    fun server() = Server(domain)

    fun repo() = Repo(domain)
}