package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolPost
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.Reference
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.atproto.admin.def.StatusAttr
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Update the service-specific admin status of a subject (account, record, or blob).
 */
class UpdateSubjectStatus(val domain: Domain) :
    AtProtocolPost<UpdateSubjectStatus.UpdateSubjectStatusRequest, UpdateSubjectStatus.UpdateSubjectStatusResponse>(
        AtProtoActions.AdminUpdateSubjectStatus,
        domain,
        UpdateSubjectStatusRequest::class,
        UpdateSubjectStatusResponse::class
    ) {
    @Serializable
    data class UpdateSubjectStatusRequest(
        @Transient
        override val adminPassword: String = "",
        val subject: Reference,
        val takedown: StatusAttr? = null
    ) : RequireAdminSession, AtProtocolPostRequestModel

    @Serializable
    data class UpdateSubjectStatusResponse(
        val subject: Reference,
        val takedown: StatusAttr? = null
    ) : AtProtocolModel
}