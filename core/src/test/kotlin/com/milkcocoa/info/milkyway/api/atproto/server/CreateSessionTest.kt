package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.domain.Domain
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class CreateSessionTest {
    @Test
    fun test() {
        runBlocking {
            Milkyway.instance(domain = Domain("https://bsky.social"))
                .atProtocol()
                .server()
                .createSession(
                    CreateSession.CreateSessionRequest(
                        identifier = System.getenv("BSKY_IDENTIFIER"),
                        password = System.getenv("BSKY_PASSWORD")
                    )
                ).let {
                    println(it)
                }
        }
    }
}