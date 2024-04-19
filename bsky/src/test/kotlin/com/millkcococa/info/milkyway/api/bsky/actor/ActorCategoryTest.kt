package com.millkcococa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.api.atproto.server.CreateSession
import com.milkcocoa.info.milkyway.api.bsky
import com.milkcocoa.info.milkyway.api.bsky.actor.GetPreferences
import com.milkcocoa.info.milkyway.api.bsky.actor.GetProfile
import com.milkcocoa.info.milkyway.api.bsky.actor.GetProfiles
import com.milkcocoa.info.milkyway.api.bsky.actor.GetSuggestions
import com.milkcocoa.info.milkyway.domain.Domain
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class ActorCategoryTest {
    @Test
    fun getProfile() {
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
                .getProfile(
                    request =
                        GetProfile.GetProfileRequest(
                            accessJwt = session.accessJwt,
                            actor = session.didDoc.id
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
                            actors = listOf(session.didDoc.id)
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