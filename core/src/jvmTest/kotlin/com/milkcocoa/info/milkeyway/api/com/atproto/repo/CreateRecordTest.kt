package com.milkcocoa.info.milkeyway.api.com.atproto.repo

import com.milkcocoa.info.milkeyway.Milkeyway
import com.milkcocoa.info.milkeyway.api.com.atproto.repo.CreateRecordRequest
import com.milkcocoa.info.milkeyway.api.com.atproto.server.CreateSession
import com.milkcocoa.info.milkeyway.api.com.atproto.server.CreateSessionRequest
import com.milkcocoa.info.milkeyway.domain.Domain
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.test.Test

class CreateRecordTest {
    @Test
    fun test(){
        runBlocking {
            val session = Milkeyway.instance(domain = Domain("https://bsky.social"))
                .atProtocol()
                .server()
                .createSession(
                    CreateSessionRequest(
                        identifier = System.getenv("BSKY_IDENTIFIER"),
                        password = System.getenv("BSKY_PASSWORD")
                    )
                )


            Milkeyway.instance(domain = Domain("https://bsky.social"))
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