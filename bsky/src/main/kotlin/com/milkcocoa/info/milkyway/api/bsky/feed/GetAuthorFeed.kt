package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.FeedViewPost
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class GetAuthorFeed(val domain: Domain) :
    AtProtocolGet<GetAuthorFeed.GetAuthorFeedRequest, GetAuthorFeed.GetAuthorFeedResponse>(
    action = BskyActions.GetAuthorFeed,
    domain = domain,
    GetAuthorFeedRequest::class,
    GetAuthorFeedResponse::class
) {
    @Serializable
    data class GetAuthorFeedRequest(
        val actor: String,
        val limit: Int = 50,
        val cursor: String = "",
        val filter: AuthorFeedFilter = AuthorFeedFilter.FilterPostsWithReplies,
    ) : AtProtocolRequest{
        @Serializable(with = AuthorFeedFilter.Companion.Serializer::class)
        enum class AuthorFeedFilter(val identifier: String) {
            FilterPostsWithReplies("posts_with_replies"),
            FilterPostsNoReplies("posts_no_replies"),
            FilterPostsWithMedia("posts_with_media"),
            Unknown("unknown")
            ;

            companion object {
                fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: Unknown

                object Serializer : KSerializer<AuthorFeedFilter> {
                    override val descriptor: SerialDescriptor
                        get() =
                        PrimitiveSerialDescriptor(
                            "filter",
                            PrimitiveKind.STRING
                        )

                    override fun deserialize(decoder: Decoder): AuthorFeedFilter {
                        val value = decoder.decodeString()
                        return AuthorFeedFilter.entries
                            .firstOrNull { it.identifier == value } ?: throw NoSuchElementException()
                    }

                    override fun serialize(
                        encoder: Encoder,
                        value: AuthorFeedFilter
                    ) {
                        encoder.encodeString(value.identifier)
                    }
                }
            }
        }
    }

    @Serializable
    data class GetAuthorFeedResponse(
        val cursor: String = "",
        val feeds: List<FeedViewPost>
    ): AtProtocolModel
}