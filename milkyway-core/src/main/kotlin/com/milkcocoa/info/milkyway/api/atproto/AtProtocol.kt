package com.milkcocoa.info.milkyway.api.atproto

import com.milkcocoa.info.milkyway.api.atproto.admin.Admin
import com.milkcocoa.info.milkyway.api.atproto.identity.Identity
import com.milkcocoa.info.milkyway.api.atproto.moderation.Moderation
import com.milkcocoa.info.milkyway.api.atproto.repo.Repo
import com.milkcocoa.info.milkyway.api.atproto.server.Server
import com.milkcocoa.info.milkyway.domain.Domain

class AtProtocol(private val domain: Domain) {
    fun server() = Server(domain)

    fun repo() = Repo(domain)

    fun admin() = Admin(domain)

    fun identity() = Identity(domain)

    fun moderation() = Moderation(domain)
}