package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.Reference
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.atproto.admin.def.StatusAttr
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        @Transient
        override val adminPassword: String = "",
        val did: Did? = null,
        val uri: AtUri? = null,
        val blob: String? = null
    ) : RequireAdminSession, AtProtocolGetRequestModel

    @Serializable
    data class GetSubjectStatusResponse(
        val subject: Reference,
        val takedown: StatusAttr? = null
    ) : AtProtocolModel
}