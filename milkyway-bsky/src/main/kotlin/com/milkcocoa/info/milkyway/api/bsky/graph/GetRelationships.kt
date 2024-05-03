package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import com.milkcocoa.info.milkyway.models.bsky.graph.defs.Relationship
import kotlinx.serialization.Serializable

/**
 * Enumerates public relationships between one account, and a list of other accounts. Does not require auth.
 */
class GetRelationships(val domain: Domain) :
    AtProtocolGet<GetRelationships.GetRelationshipsRequest, GetRelationships.GetRelationshipsResponse>(
        action = BskyActions.GetRelationships,
        domain = domain,
        GetRelationshipsRequest::class,
        GetRelationshipsResponse::class
    ) {
    @Serializable
    data class GetRelationshipsRequest(
        val limit: Int = 50,
        /**
         * Primary account requesting relationships for.
         */
        val actor: ATIdentifier,
        /**
         * List of 'other' accounts to be related back to the primary.
         */
        val others: List<ATIdentifier>
    ) : AtProtocolRequest, AtProtocolGetRequestModel

    @Serializable
    data class GetRelationshipsResponse(
        val actor: String = "",
        val relationships: List<Relationship>
    ) : AtProtocolModel
}