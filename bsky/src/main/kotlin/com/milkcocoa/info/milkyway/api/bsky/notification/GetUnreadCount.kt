package com.milkcocoa.info.milkyway.api.bsky.notification

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * Count the number of unread notifications for the requesting account. Requires auth.
 */
class GetUnreadCount(val domain: Domain) :
    AtProtocolGet<GetUnreadCount.GetUnreadCountRequest, GetUnreadCount.GetUnreadCountResponse>(
        action = BskyActions.GetUnreadCount,
        domain = domain,
        GetUnreadCountRequest::class,
        GetUnreadCountResponse::class
    ) {
    @Serializable
    data class GetUnreadCountRequest(
        override val accessJwt: String,
        @Serializable(with = SeenAtSerializer::class)
        val seenAt: LocalDateTime
    ) : AtProtocolRequestWithSession {
        companion object {
            object SeenAtSerializer : DateTimeSerializer("seenAt")
        }
    }

    @Serializable
    data class GetUnreadCountResponse(
        val count: Int
    ) : AtProtocolModel
}