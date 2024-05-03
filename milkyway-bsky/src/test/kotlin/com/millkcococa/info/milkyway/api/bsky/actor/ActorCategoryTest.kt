package com.millkcococa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.api.atproto.server.CreateSession
import com.milkcocoa.info.milkyway.api.bsky
import com.milkcocoa.info.milkyway.api.bsky.actor.GetPreferences
import com.milkcocoa.info.milkyway.api.bsky.actor.GetProfile
import com.milkcocoa.info.milkyway.api.bsky.actor.GetProfiles
import com.milkcocoa.info.milkyway.api.bsky.actor.GetSuggestions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlin.test.Test

class ActorCategoryTest {
    object ColorAsStringSerializer : KSerializer<Color> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Color", PrimitiveKind.STRING)

        override fun serialize(
            encoder: Encoder,
            value: Color
        ) {
            val string = value.rgb.toString(16).padStart(6, '0')
            encoder.encodeString(string)
        }

        override fun deserialize(decoder: Decoder): Color {
            val string = decoder.decodeString()
            return Color(string.toInt(16), Did("did:plc:sadofhrgregre"))
        }
    }

    @Serializable // (with = ColorAsStringSerializer::class)
    class Color(val rgb: Int, val did: ATIdentifier)

    @Test
    fun getProfile() {
        val green = Color(0x00ff00, Did("did:plc:sadofhrgregre"))
        println(Json.encodeToString(green))
        runBlocking {
            println(
                Json { }.encodeToString(Did.serializer(), Did("did:aud:wfgergregrgr"))
            )

            val session =
                Milkyway.instance(domain = Domain("https://bsky.social"))
                    .atProtocol()
                    .server()
                    .createSession(
                        CreateSession.CreateSessionRequest(
                            identifier = System.getenv("BSKY_IDENTIFIER"),
                            password = System.getenv("BSKY_PASSWORD")
                        )
                    )

            Milkyway.instance(domain = Domain("https://bsky.social"))
                .bsky()
                .actor()
                .getProfile(
                    request =
                        GetProfile.GetProfileRequest(
                            accessJwt = session.accessJwt,
                            actor = session.did
                        )
                )
                .let {
                    println(it)
                }
        }
    }

    @Test
    fun getProfiles() {
        runBlocking {
            val session =
                Milkyway.instance(domain = Domain("https://bsky.social"))
                    .atProtocol()
                    .server()
                    .createSession(
                        CreateSession.CreateSessionRequest(
                            identifier = System.getenv("BSKY_IDENTIFIER"),
                            password = System.getenv("BSKY_PASSWORD")
                        )
                    )

            Milkyway.instance(domain = Domain("https://bsky.social"))
                .bsky()
                .actor()
                .getProfiles(
                    request =
                        GetProfiles.GetProfilesRequest(
                            accessJwt = session.accessJwt,
                            actors = listOf(session.did)
                        )
                )
                .let {
                    println(it)
                }
        }
    }

    @Test
    fun getPreferences() {
        runBlocking {
            val session =
                Milkyway.instance(domain = Domain("https://bsky.social"))
                    .atProtocol()
                    .server()
                    .createSession(
                        CreateSession.CreateSessionRequest(
                            identifier = System.getenv("BSKY_IDENTIFIER"),
                            password = System.getenv("BSKY_PASSWORD")
                        )
                    )

            Milkyway.instance(domain = Domain("https://bsky.social"))
                .bsky()
                .actor()
                .getPReferences(
                    request =
                        GetPreferences.GetPreferencesRequest(
                            accessJwt = session.accessJwt
                        )
                )
                .let {
                    println(it)
                }
        }
    }

    @Test
    fun getSuggestions() {
        runBlocking {
            val session =
                Milkyway.instance(domain = Domain("https://bsky.social"))
                    .atProtocol()
                    .server()
                    .createSession(
                        CreateSession.CreateSessionRequest(
                            identifier = System.getenv("BSKY_IDENTIFIER"),
                            password = System.getenv("BSKY_PASSWORD")
                        )
                    )

            Milkyway.instance(domain = Domain("https://bsky.social"))
                .bsky()
                .actor()
                .getSuggestions(
                    request =
                        GetSuggestions.GetSuggestionsRequest(
                            accessJwt = session.accessJwt,
                            limit = 10
                        )
                )
                .let {
                    println(it)
                }
        }
    }
}