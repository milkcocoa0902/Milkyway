package com.milkcocoa.info.milkyway.models.did

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Operation(
    /**
     * with fixed value plc_operation
     */
    val type: String = "plc_operation",
    /**
     * priority-ordered list of public keys in did:key encoding.
     * must include least 1 key and at most 5 keys, with no duplication.
     * control of the DID identifier rests in these keys. not included in DID document.
     */
    val rotationKeys: List<String> = listOf(),
    /**
     * n atproto entry with a "blessed" public key type,
     * to be used as a signing key for authenticating updates to the account's repository.
     * The signing key does not have any control over the DID identity unless also included in the rotationKeys list.
     * Best practice is to maintain separation between rotation keys and atproto signing keys.
     */
    val verificationMethods: VerificationMethods? = null,
    /**
     * should include an at:// URI indicating a handle (hostname) for the account.
     * Note that the handle/DID mapping needs to be validated bi-directionally (via handle resolution),
     * and needs to be re-verified periodically
     */
    val alsoKnownAs: List<String> = listOf(),
    /**
     * an atproto_pds entry with an AtprotoPersonalDataServer type and http/https URL endpoint indicating
     * the account's current PDS hostname.
     * for example, https://pds.example.com (no /xrpc/ suffix needed).
     */
    val services: Services? = null,
    /**
     * a CID hash pointer to a previous operation if an update, or null for a creation.
     * If null, the key should actually be part of the object, with value null, not simply omitted.
     * In DAG-CBOR encoding, the CID is string-encoded, not a binary IPLD "Link"
     */
    val prev: String? = null,
    /**
     * signature of the operation in base64url encoding
     */
    val sig: String
){
    @Serializable
    data class Services(
        @SerialName("atproto_pds")
        val pds: PDS
    ){
        @Serializable
        data class PDS(
            val type: String,
            val endpoint: String,
        )
    }

    @Serializable
    data class VerificationMethods(
        val atproto: String? = null
    )
}