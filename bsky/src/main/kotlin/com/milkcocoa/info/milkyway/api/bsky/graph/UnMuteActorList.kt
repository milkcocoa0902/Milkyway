package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Unmutes the specified list of accounts. Requires auth.
 */
class UnMuteActorList(val domain: Domain) :
    AtProtocolUnitPost<UnMuteActorList.UnMuteActorListRequest>(
        action = BskyActions.UnMuteActorList,
        domain = domain,
        UnMuteActorListRequest::class
    ) {
    @Serializable
    data class UnMuteActorListRequest(
        @Transient
        override val accessJwt: String = "",
        val list: AtUri
    ) : RequireUserSession, AtProtocolPostRequestModel
}