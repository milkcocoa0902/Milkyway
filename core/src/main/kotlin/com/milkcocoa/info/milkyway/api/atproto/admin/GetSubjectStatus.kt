package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithAdmin
import com.milkcocoa.info.milkyway.models.Reference
import com.milkcocoa.info.milkyway.models.atproto.admin.def.StatusAttr
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import kotlinx.serialization.Serializable

/**
 * Get the service-specific admin status of a subject (account, record, or blob).
 */
class GetSubjectStatus(val domain: Domain) :
    AtProtocolGet<GetSubjectStatus.GetSubjectStatusRequest, GetSubjectStatus.GetSubjectStatusResponse>(
        AtProtoActions.AdminGetSubjectStatus,
        domain,
        GetSubjectStatusRequest::class,
        GetSubjectStatusResponse::class
    ) {
    @Serializable
    data class GetSubjectStatusRequest(
        override val adminPassword: String,
        val did: String? = null,
        val uri: AtUri? = null,
        val blob: String? = null
    ) : AtProtocolRequestWithAdmin

    @Serializable
    data class GetSubjectStatusResponse(
        val subject: Reference,
        val takedown: StatusAttr? = null
    ) : AtProtocolModel
}