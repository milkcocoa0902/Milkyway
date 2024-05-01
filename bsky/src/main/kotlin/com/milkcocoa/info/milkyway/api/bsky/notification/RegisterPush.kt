package com.milkcocoa.info.milkyway.api.bsky.notification

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Register to receive push notifications, via a specified service, for the requesting account. Requires auth.
 */
class RegisterPush(val domain: Domain) :
    AtProtocolUnitPost<RegisterPush.RegisterPushRequest>(
        action = BskyActions.RegisterPush,
        domain = domain,
        RegisterPushRequest::class
    ) {
    @Serializable
    data class RegisterPushRequest(
        override val accessJwt: String,
        val serviceDid: Did,
        val token: String,
        val platform: Platform,
        val appId: String
    ) : RequireUserSession, AtProtocolPostRequestModel {
        @Serializable
        enum class Platform {
            @Suppress("ktlint:standard:enum-entry-name-case")
            @SerialName("ios")
            iOS,

            @SerialName("android")
            Android,

            @SerialName("web")
            Web
        }
    }
}