package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
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
        override val accessJwt: String,
        val uri: String,
        val cid: String,
        val limit: Int = 50,
        val cursor: String = ""
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetRepostedByResponse(
        val uri: String,
        val cid: String = "",
        val cursor: String = "",
        val repostedBy: List<ProfileView>
    ) : AtProtocolModel
}