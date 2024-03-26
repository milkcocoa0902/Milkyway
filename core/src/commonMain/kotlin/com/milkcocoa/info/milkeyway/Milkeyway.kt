package com.milkcocoa.info.milkeyway

import com.milkcocoa.info.milkeyway.api.AtProtocol
import com.milkcocoa.info.milkeyway.domain.Domain

class Milkeyway private constructor(private val domain: Domain) {
    companion object{
        fun instance(domain: Domain) = Milkeyway(domain)
    }

    fun atProtocol() = AtProtocol(domain = domain)
}