package com.millkcococa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.api.atproto.server.CreateSession
import com.milkcocoa.info.milkyway.api.bsky
import com.milkcocoa.info.milkyway.api.bsky.feed.GetTimeLine
import com.milkcocoa.info.milkyway.domain.Domain
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.test.Test

class GetTimelineTest {
    @Test
    fun test(){
        runBlocking {
            val session = Milkyway.instance(domain = Domain("https://bsky.social"))
                .atProtocol()
                .server()
                .createSession(
                    CreateSession.CreateSessionRequest(
                        identifier = System.getenv("BSKY_IDENTIFIER"),
                        password = System.getenv("BSKY_PASSWORD")
                    )
                )


            Milkyway.instance(domain = Domain("https://bsky.social"))
                .bsky()
                .feed()
                .getTimeline(
                    GetTimeLine.GetTimelineRequest(
                        accessJwt = session.accessJwt,
                        cursor = "",
                        limit = 10
                    )
                ).let {
                    println(it)
                }
        }
    }
}