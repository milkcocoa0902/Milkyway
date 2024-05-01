package com.milkcocoa.info.milkyway.api.atproto.identity

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.did.Operation
import kotlinx.serialization.Serializable

/**
 * Validates a PLC operation to ensure that it doesn't violate a service's constraints
 * or get the identity into a bad state,
 * then submits it to the PLC registry
 */
class SubmitPlcOperation(val domain: Domain) :
    AtProtocolUnitPost<SubmitPlcOperation.SubmitPlcOperationRequest>(
        AtProtoActions.SubmitPlcOperation,
        domain,
        SubmitPlcOperationRequest::class
    ) {
    @Serializable
    data class SubmitPlcOperationRequest(
        override val accessJwt: String,
        val operation: Operation
    ) : RequireUserSession, AtProtocolPostRequestModel
}