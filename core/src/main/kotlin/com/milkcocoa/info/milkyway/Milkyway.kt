package com.milkcocoa.info.milkyway

import com.milkcocoa.info.milkyway.api.atproto.AtProtocol
import com.milkcocoa.info.milkyway.domain.Domain

class Milkyway private constructor(val domain: Domain) {
    companion object {
        fun instance(domain: Domain) = Milkyway(domain)
    }

    fun atProtocol() = AtProtocol(domain = domain)
}