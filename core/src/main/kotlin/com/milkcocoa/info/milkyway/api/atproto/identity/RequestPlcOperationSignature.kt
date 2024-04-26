package com.milkcocoa.info.milkyway.api.atproto.identity

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import kotlinx.serialization.Serializable

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
        override val accessJwt: String
    ) : AtProtocolRequestWithSession
}