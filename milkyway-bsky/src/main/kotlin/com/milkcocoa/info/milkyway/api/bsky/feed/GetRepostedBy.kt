package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetRepostedBy(val domain: Domain) :
    AtProtocolGet<GetRepostedBy.GetRepostedByRequest, GetRepostedBy.GetRepostedByResponse>(
        action = BskyActions.GetRepostedBy,
        domain = domain,
        GetRepostedByRequest::class,
        GetRepostedByResponse::class
    ) {
    @Serializable
    data class GetRepostedByRequest(
        @Transient
        override val accessJwt: String = "",
        val uri: AtUri,
        val cid: String,
        val limit: Int = 50,
        val cursor: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetRepostedByResponse(
        val uri: String,
        val cid: String = "",
        val cursor: String = "",
        val repostedBy: List<ProfileView>
    ) : AtProtocolModel
}