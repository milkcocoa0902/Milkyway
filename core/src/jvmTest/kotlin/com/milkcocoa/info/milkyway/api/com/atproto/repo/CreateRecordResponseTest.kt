package com.milkcocoa.info.milkyway.api.com.atproto.repo

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.atproto.models.com.atproto.repo.CreateRecordRequest
import com.milkcocoa.info.milkyway.atproto.models.com.atproto.server.CreateSessionRequest
import com.milkcocoa.info.milkyway.domain.Domain
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.test.Test

class CreateRecordResponseTest {
    @Test
    fun test(){
        runBlocking {
            val session = Milkyway.instance(domain = Domain("https://bsky.social"))
                .atProtocol()
                .server()
                .createSession(
                    CreateSessionRequest(
                        identifier = System.getenv("BSKY_IDENTIFIER"),
                        password = System.getenv("BSKY_PASSWORD")
                    )
                )


            Milkyway.instance(domain = Domain("https://bsky.social"))
                .atProtocol()
                .repo()
                .createRecord(
                    CreateRecordRequest(
                        repo = session.handle,
                        collection = "app.bsky.feed.post",
                        record = CreateRecordRequest.Record(
                            text = "hello from Milkeyway",
                            createdAt = LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
                        )
                    ),
                    accessJwt = session.accessJwt
                )
        }
    }
}