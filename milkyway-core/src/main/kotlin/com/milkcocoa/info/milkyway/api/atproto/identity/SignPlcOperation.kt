package com.milkcocoa.info.milkyway.api.atproto.identity

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolPost
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.did.Operation
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        @Transient
        override val accessJwt: String = "",
        /**
         * A token received through com.atproto.identity.requestPlcOperationSignature
         */
        val token: String,
        val rotationKeys: List<String> = emptyList(),
        val alsoKnownAs: List<String> = emptyList(),
        val verificationMethods: Operation.VerificationMethods? = null,
        val services: Operation.Services? = null
    ) : RequireUserSession, AtProtocolPostRequestModel

    @Serializable
    data class SignPlcOperationResponse(
        val operation: Operation
    ) : AtProtocolModel
}