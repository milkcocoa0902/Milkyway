package com.milkcocoa.info.milkyway.api.atproto.identity

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.did.Operation
import kotlinx.serialization.Serializable

/**
 * Signs a PLC operation to update some value(s) in the requesting DID's document.
 */
class SignPlcOperation(val domain: Domain) :
    AtProtocolPost<SignPlcOperation.SignPlcOperationRequest, SignPlcOperation.SignPlcOperationResponse>(
        AtProtoActions.SignPlcOperation,
        domain,
        SignPlcOperationRequest::class,
        SignPlcOperationResponse::class
    ) {
    @Serializable
    data class SignPlcOperationRequest(
        override val accessJwt: String,
        /**
         * A token received through com.atproto.identity.requestPlcOperationSignature
         */
        val token: String,
        val rotationKeys: List<String> = emptyList(),
        val alsoKnownAs: List<String> = emptyList(),
        val verificationMethods: Operation.VerificationMethods? = null,
        val services: Operation.Services? = null
    ) : AtProtocolRequestWithSession

    @Serializable
    data class SignPlcOperationResponse(
        val operation: Operation
    ) : AtProtocolModel
}