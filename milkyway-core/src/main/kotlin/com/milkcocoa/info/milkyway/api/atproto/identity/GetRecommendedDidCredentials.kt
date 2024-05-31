package com.milkcocoa.info.milkyway.api.atproto.identity

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.did.Operation
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        @Transient
        override val accessJwt: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetRecommendedDidCredentialsResponse(
        val rotationKeys: List<String> = emptyList(),
        val alsoKnownAs: List<String> = emptyList(),
        val verificationMethods: Operation.VerificationMethods? = null,
        val services: Operation.Services? = null
    ) : AtProtocolModel
}