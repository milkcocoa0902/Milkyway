package com.milkcocoa.info.milkyway.api

import com.milkcocoa.info.milkyway.api.com.atproto.repo.Repo
import com.milkcocoa.info.milkyway.api.com.atproto.server.Server
import com.milkcocoa.info.milkyway.domain.Domain

class AtProtocol(private val domain: Domain) {
    fun server() = Server(domain)
    fun repo() = Repo(domain)
}