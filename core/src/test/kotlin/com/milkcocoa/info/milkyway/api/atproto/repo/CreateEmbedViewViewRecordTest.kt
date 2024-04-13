package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.api.atproto.server.CreateSession
import com.milkcocoa.info.milkyway.domain.Domain
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.test.Test

class CreateEmbedViewViewRecordTest {
    @Test
    fun test() {
        runBlocking {
            val session =
                Milkyway.instance(domain = Domain("https://bsky.social"))
                    .atProtocol()
                    .server()
                    .createSession(
                        CreateSession.CreateSessionRequest(
                            identifier = System.getenv("BSKY_IDENTIFIER"),
                            password = System.getenv("BSKY_PASSWORD")
                        )
                    )

            Milkyway.instance(domain = Domain("https://bsky.social"))
                .atProtocol()
                .repo()
                .createRecord(
                    CreateRecord.CreateRecordRequest(
                        accessJwt = session.accessJwt,
                        repo = session.handle,
                        collection = "app.bsky.feed.post",
                        record =
                            CreateRecord.CreateRecordRequest.Record(
                                text = "hello from Milkeyway",
                                createdAt =
                                    LocalDateTime.now(ZoneOffset.UTC)
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
                            )
                    )
                )
        }
    }
}