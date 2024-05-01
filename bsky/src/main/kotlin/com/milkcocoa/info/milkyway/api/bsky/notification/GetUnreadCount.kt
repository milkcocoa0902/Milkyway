package com.milkcocoa.info.milkyway.api.bsky.notification

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
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
        @Serializable(with = DateTimeSerializer::class)
        val seenAt: LocalDateTime
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetUnreadCountResponse(
        val count: Int
    ) : AtProtocolModel
}