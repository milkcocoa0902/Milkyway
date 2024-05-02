package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.LocalDateTime

class GetLikes(val domain: Domain) : AtProtocolGet<GetLikes.GetLikesRequest, GetLikes.GetLikesResponse>(
    action = BskyActions.GetLikes,
    domain = domain,
    GetLikesRequest::class,
    GetLikesResponse::class
) {
    @Serializable
    data class GetLikesRequest(
        @Transient
        override val accessJwt: String = "",
        val uri: AtUri,
        val cid: String,
        val limit: Int = 50,
        val cursor: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetLikesResponse(
        val uri: AtUri,
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