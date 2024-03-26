package com.milkcocoa.info.milkeyway.api.com.atproto.server

import com.milkcocoa.info.milkeyway.Milkeyway
import com.milkcocoa.info.milkeyway.api.com.atproto.server.CreateSession
import com.milkcocoa.info.milkeyway.api.com.atproto.server.CreateSessionRequest
import com.milkcocoa.info.milkeyway.domain.Domain
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class CreateSessionTest {
    @Test
    fun test(){
        runBlocking {
            Milkeyway.instance(domain = Domain("https://bsky.social"))
                .atProtocol()
                .server()
                .createSession(
                    CreateSessionRequest(
                        identifier = System.getenv("BSKY_IDENTIFIER"),
                        password = System.getenv("BSKY_PASSWORD")
                    )
                ).let {
                    println(it)
                }
        }
    }
}