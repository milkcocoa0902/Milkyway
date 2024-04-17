package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.AtProtocolUnit
import kotlinx.serialization.Serializable

/**
 * Unmutes the specified list of accounts. Requires auth.
 */
class UnMuteActorList(val domain: Domain):
    AtProtocolPost<UnMuteActorList.UnMuteActorListRequest, AtProtocolUnit>(
    action = BskyActions.UnMuteActorList,
    domain = domain,
    UnMuteActorListRequest::class,
    AtProtocolUnit::class
) {
    @Serializable
    data class UnMuteActorListRequest(
        override val accessJwt: String,
        val list: List<String>
    ) : AtProtocolRequestWithSession
}