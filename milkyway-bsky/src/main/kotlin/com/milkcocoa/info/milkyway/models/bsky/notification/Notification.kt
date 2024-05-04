package com.milkcocoa.info.milkyway.models.bsky.notification

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import com.milkcocoa.info.milkyway.types.BskyRecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Notification(
    val uri: String,
    val cid: String,
    val author: ProfileView,
    val reason: NotificationReason? = null,
    val reasonSubject: String? = null,
    val record: Record<BskyRecordType>,
    val isRead: Boolean,
    @Serializable(with = DateTimeSerializer::class)
    val indexedAt: LocalDateTime,
    val labels: List<Label> = emptyList()
)