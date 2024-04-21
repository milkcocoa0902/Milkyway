package com.milkcocoa.info.milkyway.models.bsky.record.labeler

import com.milkcocoa.info.milkyway.models.atproto.label.defs.SelfLabels
import com.milkcocoa.info.milkyway.models.bsky.labeler.defs.LabelerPolicies
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * A declaration of the existence of labeler service.
 */
@SerialName("app.bsky.labeler.service")
@Serializable
data class ServiceRecord(
    val policies: LabelerPolicies,
    val labels: SelfLabels? = null,
    @Serializable(with = CreatedAtSerializer::class)
    val createdAt: LocalDateTime
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.LabelerServiceRecord

    companion object {
        object CreatedAtSerializer : DateTimeSerializer("createdAt")
    }
}