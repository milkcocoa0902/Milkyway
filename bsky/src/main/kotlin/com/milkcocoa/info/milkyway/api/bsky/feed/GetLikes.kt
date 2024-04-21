package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

class GetLikes(val domain: Domain) : AtProtocolGet<GetLikes.GetLikesRequest, GetLikes.GetLikesResponse>(
    action = BskyActions.GetLikes,
    domain = domain,
    GetLikesRequest::class,
    GetLikesResponse::class
) {
    @Serializable
    data class GetLikesRequest(
        override val accessJwt: String,
        val uri: String,
        val cid: String,
        val limit: Int = 50,
        val cursor: String = ""
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetLikesResponse(
        val uri: String,
        val cid: String = "",
        val cursor: String = "",
        val likes: List<Likes>
    ) : AtProtocolModel {
        @Serializable
        data class Likes(
            @Serializable(with = DateTimeSerializer::class)
            val indexedAt: LocalDateTime,
            @Serializable(with = DateTimeSerializer::class)
            val createdAt: LocalDateTime,
            val actor: ProfileView
        )
    }
}