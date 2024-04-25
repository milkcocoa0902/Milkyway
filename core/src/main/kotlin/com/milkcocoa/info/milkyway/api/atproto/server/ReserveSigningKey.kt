package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import kotlinx.serialization.Serializable

/**
 * Reserve a repo signing key, for use with account creation.
 * Necessary so that a DID PLC update operation can be constructed during an account migraiton.
 * Public and does not require auth; implemented by PDS.
 * NOTE: this endpoint may change when full account migration is implemented.
 */
class ReserveSigningKey(val domain: Domain) :
    AtProtocolPost<ReserveSigningKey.ReserveSigningKeyRequest, ReserveSigningKey.ReserveSigningKeyResponse>(
        AtProtoActions.ReserveSigningKey,
        domain,
        ReserveSigningKeyRequest::class,
        ReserveSigningKeyResponse::class
    ) {
    @Serializable
    data class ReserveSigningKeyRequest(
        /**
         * The DID to reserve a key for.
         */
        val did: String
    ) : AtProtocolRequest

    @Serializable
    data class ReserveSigningKeyResponse(
        /**
         * The public key for the reserved signing key, in did:key serialization.
         */
        val signingKey: String
    ) : AtProtocolModel
}