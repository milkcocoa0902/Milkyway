package com.milkcocoa.info.milkyway.api.atproto.identity

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Request an email with a code to in order to request a signed PLC operation. Requires Auth.
 */
class RequestPlcOperationSignature(val domain: Domain) :
    AtProtocolUnitPost<RequestPlcOperationSignature.RequestPlcOperationSignatureRequest>(
        AtProtoActions.RequestPlcOperationSignature,
        domain,
        RequestPlcOperationSignatureRequest::class
    ) {
    @Serializable
    data class RequestPlcOperationSignatureRequest(
        @Transient
        override val accessJwt: String = ""
    ) : RequireUserSession, AtProtocolPostRequestModel
}