package com.milkcocoa.info.milkyway.api.bsky.notification

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.LocalDateTime

/**
 * Notify server that the requesting account has seen notifications. Requires auth.
 */
class UpdateSeen(val domain: Domain) :
    AtProtocolUnitPost<UpdateSeen.UpdateSeenRequest>(
        action = BskyActions.UpdateSeen,
        domain = domain,
        UpdateSeenRequest::class
    ) {
    @Serializable
    data class UpdateSeenRequest(
        @Transient
        override val accessJwt: String = "",
        @Serializable(with = DateTimeSerializer::class)
        val seenAt: LocalDateTime
    ) : RequireUserSession, AtProtocolPostRequestModel
}