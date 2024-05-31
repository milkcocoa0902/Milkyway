package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable

/**
 * Describes the server's account creation requirements and capabilities. Implemented by PDS.
 */
class DescribeServer(val domain: Domain) :
    AtProtocolGet<DescribeServer.DescribeServerRequest, DescribeServer.DescribeServerResponse>(
        AtProtoActions.DescribeServer,
        domain,
        DescribeServerRequest::class,
        DescribeServerResponse::class
    ) {
    @Serializable
    class DescribeServerRequest : AtProtocolRequest, AtProtocolGetRequestModel

    @Serializable
    data class DescribeServerResponse(
        val inviteCodeRequired: Boolean? = null,
        val phoneVerificationRequired: Boolean? = null,
        val availableUserDomains: List<String>,
        val links: Links? = null,
        val contact: Contact? = null,
        val did: Did
    ) : AtProtocolModel {
        @Serializable
        data class Links(
            val privacyPolicy: String? = null,
            val termsOfService: String? = null
        )

        @Serializable
        data class Contact(
            val email: String? = null
        )
    }
}