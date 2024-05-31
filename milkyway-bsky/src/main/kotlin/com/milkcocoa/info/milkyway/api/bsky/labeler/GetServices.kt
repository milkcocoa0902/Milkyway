package com.milkcocoa.info.milkyway.api.bsky.labeler

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.bsky.labeler.Labeler
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Get information about a list of labeler services.
 */
class GetServices(val domain: Domain) :
    AtProtocolGet<GetServices.GetServicesRequest, GetServices.GetServicesResponse>(
        action = BskyActions.GetServices,
        domain = domain,
        GetServicesRequest::class,
        GetServicesResponse::class
    ) {
    @Serializable
    data class GetServicesRequest(
        @Transient
        override val accessJwt: String = "",
        val dids: List<Did>,
        val detailed: Boolean = false
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetServicesResponse(
        val views: List<Labeler>
    ) : AtProtocolModel
}