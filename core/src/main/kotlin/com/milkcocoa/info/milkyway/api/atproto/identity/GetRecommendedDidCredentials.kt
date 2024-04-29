package com.milkcocoa.info.milkyway.api.atproto.identity

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.did.Operation
import kotlinx.serialization.Serializable

/**
 * Describe the credentials that should be included in the DID doc of an account that is migrating to this service.
 */
class GetRecommendedDidCredentials(val domain: Domain) :
    AtProtocolGet<
        GetRecommendedDidCredentials.GetRecommendedDidCredentialsRequest,
        GetRecommendedDidCredentials.GetRecommendedDidCredentialsResponse
    >(
        AtProtoActions.GetRecommendedDidCredentials,
        domain,
        GetRecommendedDidCredentialsRequest::class,
        GetRecommendedDidCredentialsResponse::class
    ) {
    @Serializable
    data class GetRecommendedDidCredentialsRequest(
        override val accessJwt: String
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetRecommendedDidCredentialsResponse(
        val rotationKeys: List<String> = emptyList(),
        val alsoKnownAs: List<String> = emptyList(),
        val verificationMethods: Operation.VerificationMethods? = null,
        val services: Operation.Services? = null
    ) : AtProtocolModel
}