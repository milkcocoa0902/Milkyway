package com.milkcocoa.info.milkyway.api.bsky.notification

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
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
        override val accessJwt: String,
        @Serializable(with = SeenAtSerializer::class)
        val seenAt: LocalDateTime
    ) : AtProtocolRequestWithSession {
        companion object {
            object SeenAtSerializer : DateTimeSerializer("seenAt")
        }
    }
}