package com.milkcocoa.info.milkyway.sample

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.api.atproto.server.CreateSession
import com.milkcocoa.info.milkyway.api.atproto.server.RefreshSession
import com.milkcocoa.info.milkyway.api.bsky
import com.milkcocoa.info.milkyway.api.bsky.actor.GetProfile
import com.milkcocoa.info.milkyway.api.bsky.feed.GetPostThread
import com.milkcocoa.info.milkyway.api.bsky.feed.GetTimeLine
import com.milkcocoa.info.milkyway.api.installBskyDependencies
import com.milkcocoa.info.milkyway.domain.OfficialDomain
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.record.feed.FeedPostRecord

suspend fun main() {
    Milkyway.installBskyDependencies()
    val client = Milkyway.instance(domain = OfficialDomain.BskySocial)
    val session =
        getSession(
            client = client,
            identifier = System.getenv("BSKY_IDENTIFIER"), // replace your own identifier. example - bsky.milkcocoa.info
            password = System.getenv("BSKY_PASSWORD") // replace your own password
        )

//    val blob =
//        client.atProtocol()
//            .repo()
//            .uploadBlob(
//                request =
//                    UploadBlob.UploadBlobRequest(
//                        accessJwt = session.accessJwt,
//                        binary = Files.readAllBytes(Path("/home/keita/Pictures/Screenshot_20230303_213332_.png"))
//                    )
//            )
//
//    runCatching {
//        client.atProtocol()
//            .repo()
//            .createRecord(
//                request =
//                    CreateRecord.CreateRecordRequest(
//                        accessJwt = session.accessJwt,
//                        repo = session.handle.value,
//                        collection = NSID("app.bsky.feed.post"),
//                        record =
//                            FeedPostRecord(
//                                text = "post from milkyway!!!!",
//                                createdAt = LocalDateTime.now(),
//                                embed =
//                                    ImageEmbed(
//                                        images =
//                                            listOf(
//                                                Image(
//                                                    alt = "",
//                                                    image = blob.blob
//                                                )
//                                            )
//                                    )
//                            )
//                    )
//            )
//    }.getOrElse {
//        println(it.message)
//    }

    val timeline =
        client.bsky()
            .feed()
            .getTimeline(
                GetTimeLine.GetTimelineRequest(
                    accessJwt = session.accessJwt,
                    cursor = "",
                    limit = 10
                )
            )

    for (f in timeline.feed) {
        when (f.post.record) {
            is FeedPostRecord -> println((f.post.record as FeedPostRecord).text)
            else -> println(f.post.record.type)
        }
    }

    client.bsky()
        .feed()
        .getPostThread(
            request =
                GetPostThread.GetPostThreadRequest(
                    accessJwt = session.accessJwt,
                    uri = AtUri("at://did:plc:nu7c2zvznwcaowvdssvevwff/app.bsky.feed.post/3kta2wa57jl2o")
                )
        ).let {
            println(it)
        }
}

suspend fun getSession(
    client: Milkyway,
    identifier: String,
    password: String
) = client.atProtocol()
    .server()
    .createSession(
        CreateSession.CreateSessionRequest(
            identifier = identifier,
            password = password
        )
    )

suspend fun getProfile(
    client: Milkyway,
    accessJwt: String,
    actor: ATIdentifier
) = client.bsky()
    .actor()
    .getProfile(
        request =
            GetProfile.GetProfileRequest(
                accessJwt = accessJwt,
                actor = actor
            )
    )

suspend fun refreshJwt(
    client: Milkyway,
    refreshJwt: String
) = client.atProtocol()
    .server()
    .refreshSession(
        request =
            RefreshSession.RefreshSessionRequest(
                refreshJwt = refreshJwt
            )
    )