package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.AtProtocolUnit
import kotlinx.serialization.Serializable

/**
 * Creates a mute relationship for the specified list of accounts. Mutes are private in Bluesky. Requires auth.
 */
class MuteActorList(val domain: Domain):
    AtProtocolPost<MuteActorList.MuteActorListRequest, AtProtocolUnit>(
    action = BskyActions.MuteActorList,
    domain = domain,
    MuteActorListRequest::class,
    AtProtocolUnit::class
) {
    @Serializable
    data class MuteActorListRequest(
        override val accessJwt: String,
        val list: List<String>
    ) : AtProtocolRequestWithSession
}