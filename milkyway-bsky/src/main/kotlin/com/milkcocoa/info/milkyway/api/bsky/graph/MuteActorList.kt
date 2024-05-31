package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Creates a mute relationship for the specified list of accounts. Mutes are private in Bluesky. Requires auth.
 */
class MuteActorList(val domain: Domain) :
    AtProtocolUnitPost<MuteActorList.MuteActorListRequest>(
        action = BskyActions.MuteActorList,
        domain = domain,
        MuteActorListRequest::class
    ) {
    @Serializable
    data class MuteActorListRequest(
        @Transient
        override val accessJwt: String = "",
        val list: AtUri
    ) : RequireUserSession, AtProtocolPostRequestModel
}