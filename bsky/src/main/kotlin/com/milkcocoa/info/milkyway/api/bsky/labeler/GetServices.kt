package com.milkcocoa.info.milkyway.api.bsky.labeler

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.labeler.Labeler
import kotlinx.serialization.Serializable

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
        override val accessJwt: String,
        val dids: List<String>,
        val detailed: Boolean = false
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetServicesResponse(
        val views: List<Labeler>
    ) : AtProtocolModel
}