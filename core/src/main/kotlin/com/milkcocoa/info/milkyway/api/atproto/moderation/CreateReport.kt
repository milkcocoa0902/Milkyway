package com.milkcocoa.info.milkyway.api.atproto.moderation

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.Reference
import com.milkcocoa.info.milkyway.models.atproto.moderation.defs.ReasonType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

class CreateReport(domain: Domain) :
    AtProtocolPost<CreateReport.CreateReportRequest, CreateReport.CreateReportResponse>(
        AtProtoActions.CreateReport,
        domain,
        CreateReportRequest::class,
        CreateReportResponse::class
    ) {
    @Serializable
    data class CreateReportRequest(
        override val accessJwt: String,
        val reasonType: ReasonType,
        val reason: String,
        val subject: Reference
    ) : AtProtocolRequestWithSession

    @Serializable
    data class CreateReportResponse(
        val id: Int,
        val reasonType: ReasonType? = null,
        val reason: String? = null,
        val subject: Reference,
        val reportedBy: String,
        @Serializable(with = DateTimeSerializer::class)
        val createdAt: LocalDateTime
    ) : AtProtocolModel
}