package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.did.DidDoc
import kotlinx.serialization.Serializable

/**
 * Create an account. Implemented by PDS.
 */
class CreateAccount(val domain: Domain) :
    AtProtocolPost<CreateAccount.CreateAccountRequest, CreateAccount.CreateAccountResponse>(
        AtProtoActions.CreateAccount,
        domain,
        CreateAccountRequest::class,
        CreateAccountResponse::class
    ) {
    @Serializable
    data class CreateAccountRequest(
        val email: String? = null,
        /**
         * Requested handle for the account.
         */
        val handle: String,
        /**
         * Pre-existing atproto DID, being imported to a new account.
         */
        val did: String? = null,
        val inviteCode: String? = null,
        val verificationCode: String? = null,
        val verificationPhone: String? = null,
        /**
         * Initial account password. May need to meet instance-specific password strength requirements.
         */
        val password: String? = null,
        /**
         * DID PLC rotation key (aka, recovery key) to be included in PLC creation operation.
         */
        val recoveryKey: String? = null
//        val plcOp
    ) : AtProtocolRequest

    @Serializable
    data class CreateAccountResponse(
        val accessJwt: String,
        val refreshJwt: String,
        val handle: String,
        val did: String,
        val didDoc: DidDoc? = null
    ) : AtProtocolModel
}