package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.aturi.Handle
import com.milkcocoa.info.milkyway.models.did.DidDoc
import kotlinx.serialization.Serializable

/**
 * Get information about an account and repository, including the list of collections. Does not require auth.
 */
class DescribeRepo(val domain: Domain) :
    AtProtocolGet<DescribeRepo.DescribeRepoRequest, DescribeRepo.DescribeRepoResponse>(
        AtProtoActions.DescribeRepo,
        domain,
        DescribeRepoRequest::class,
        DescribeRepoResponse::class
    ) {
    @Serializable
    data class DescribeRepoRequest(
        /**
         * The handle or DID of the repo.
         */
        val repo: String
    ) : AtProtocolRequest, AtProtocolGetRequestModel

    @Serializable
    data class DescribeRepoResponse(
        val handle: Handle,
        val did: Did,
        val didDoc: DidDoc,
        val collections: List<String>,
        /**
         * Indicates if handle is currently valid (resolves bi-directionally)
         */
        val handleIsCorrect: Boolean
    ) : AtProtocolModel
}