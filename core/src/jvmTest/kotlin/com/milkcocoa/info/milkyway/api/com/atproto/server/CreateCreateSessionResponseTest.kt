package com.milkcocoa.info.milkyway.api.com.atproto.server

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.atproto.models.com.atproto.server.CreateSessionRequest
import com.milkcocoa.info.milkyway.domain.Domain
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class CreateCreateSessionResponseTest {
    @Test
    fun test(){
        runBlocking {
            Milkyway.instance(domain = Domain("https://bsky.social"))
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