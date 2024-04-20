package com.milkcocoa.info.milkyway.models.bsky.record.actor

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.atproto.label.defs.SelfLabels
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.FeedPostRecord
import com.milkcocoa.info.milkyway.models.entity.Blob
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

/**
 * A declaration of a Bluesky account profile.
 */
@SerialName("app.bsky.actor.profile")
@Serializable
data class ProfileRecord(
    val displayName: String,
    /**
     * Free-form profile description text
     */
    val description: String,
    /**
     * Small image to be displayed next to posts from account. AKA, 'profile picture'
     */
    val avatar: Blob?,
    /**
     * Larger horizontal image to display behind profile view.
     */
    val banner: Blob?,
    /**
     * Self-label values, specific to the Bluesky application, on the overall account.
     */
    val labels: SelfLabels
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.ProfileRecord
}