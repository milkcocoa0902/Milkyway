package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Returns the status of an account, especially as pertaining to import or recovery.
 * Can be called many times over the course of an account migration.
 * Requires auth and can only be called pertaining to oneself.
 */
class CheckAccountStatus(val domain: Domain) :
    AtProtocolGet<CheckAccountStatus.CheckAccountStatusRequest, CheckAccountStatus.CheckAccountStatusResponse>(
        AtProtoActions.CheckAccountStatus,
        domain,
        CheckAccountStatusRequest::class,
        CheckAccountStatusResponse::class
    ) {
    @Serializable
    data class CheckAccountStatusRequest(
        @Transient
        override val accessJwt: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class CheckAccountStatusResponse(
        val activated: Boolean,
        val validDid: Boolean,
        val repoCommit: String,
        val repoRev: String,
        val repoBlocks: Int,
        val indexedRecords: Int,
        val privateStateValues: Int,
        val expectedBlocks: Int,
        val importedBlocks: Int
    ) : AtProtocolModel
}