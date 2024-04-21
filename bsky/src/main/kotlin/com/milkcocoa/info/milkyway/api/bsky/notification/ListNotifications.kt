package com.milkcocoa.info.milkyway.api.bsky.notification

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.notification.Notification
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.LocalDateTime

/**
 * Enumerate notifications for the requesting account. Requires auth.
 */
class ListNotifications(val domain: Domain) :
    AtProtocolGet<ListNotifications.ListNotificationsRequest, ListNotifications.ListNotificationsResponse>(
        action = BskyActions.ListNotifications,
        domain = domain,
        ListNotificationsRequest::class,
        ListNotificationsResponse::class
    ) {
    @Serializable
    data class ListNotificationsRequest(
        override val accessJwt: String,
        val limit: Int = 50,
        val cursor: String? = null,
        @Serializable(with = DateTimeSerializer::class)
        val seenAt: LocalDateTime
    ) : AtProtocolRequestWithSession {
    }

    @Serializable
    data class ListNotificationsResponse(
        val cursor: String? = null,
        val notifications: List<Notification>,
        @Serializable(with = DateTimeSerializer::class)
        val seenAt: LocalDateTime
    ) : AtProtocolModel {
    }
}