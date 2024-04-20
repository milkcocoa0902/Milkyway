package com.milkcocoa.info.milkyway.models.bsky.record.actor

import com.milkcocoa.info.milkyway.models.atproto.label.defs.SelfLabels
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.models.entity.BlobObject
import com.milkcocoa.info.milkyway.types.RecordType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val avatar: BlobObject?,
    /**
     * Larger horizontal image to display behind profile view.
     */
    val banner: BlobObject?,
    /**
     * Self-label values, specific to the Bluesky application, on the overall account.
     */
    val labels: SelfLabels
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.ProfileRecord
}