package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.PostView
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime

class SearchPosts(val domain: Domain) :
    AtProtocolGet<SearchPosts.SearchPostsRequest, SearchPosts.SearchPostsResponse>(
        action = BskyActions.SearchPosts,
        domain = domain,
        SearchPostsRequest::class,
        SearchPostsResponse::class
) {
    @Serializable
    data class SearchPostsRequest(
        override val accessJwt: String,
        /**
         * Search query string; syntax, phrase, boolean, and faceting is unspecified, but Lucene query syntax is recommended.
         */
        val q: String,

        /**
         * Specifies the ranking order of results.
         */
        val sort: SortedBy? = null,

        /**
         * Filter results for posts after the indicated datetime (inclusive). Expected to use 'sortAt' timestamp, which may not match 'createdAt'. Can be a datetime, or just an ISO date (YYYY-MM-DD).
         */
        @Serializable(with = SinceSerializer::class)
        val since: LocalDateTime? = null,

        /**
         * Filter results for posts before the indicated datetime (not inclusive). Expected to use 'sortAt' timestamp, which may not match 'createdAt'. Can be a datetime, or just an ISO date (YYY-MM-DD).
         */
        @Serializable(with = UntilSerializer::class)
        val until: LocalDateTime? = null,

        /**
         * Filter to posts which mention the given account. Handles are resolved to DID before query-time. Only matches rich-text facet mentions.
         */
        val mentions: String? = null,
        /**
         * Filter to posts by the given account. Handles are resolved to DID before query-time.
         */
        val author: String? = null,

        /**
         * Filter to posts in the given language. Expected to be based on post language field, though server may override language detection.
         */
        val lang: String? = null,

        /**
         * Filter to posts with URLs (facet links or embeds) linking to the given domain (hostname). Server may apply hostname normalization.
         */
        val domain: String? = null,

        /**
         * Filter to posts with links (facet links or embeds) pointing to this URL. Server may apply URL normalization or fuzzy matching.
         */
        val url: String? = null,

        /**
         * Filter to posts with the given tag (hashtag), based on rich-text facet or tag field. Do not include the hash (#) prefix. Multiple tags can be specified, with 'AND' matching.
         */
        val tag: List<String>? = null,


        val limit: Int = 25,
        val cursor: String = ""
    ) : AtProtocolRequestWithSession{

        @Serializable(with = SortedBy.Companion.Serializer::class)
        enum class SortedBy(val identifier: String) {
            Top("top"),
            Latest("latest"),
            Unknown("unknown")
            ;

            companion object {
                fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: Unknown

                object Serializer : KSerializer<SortedBy> {
                    override val descriptor: SerialDescriptor
                        get() =
                            PrimitiveSerialDescriptor(
                                "sort",
                                PrimitiveKind.STRING
                            )

                    override fun deserialize(decoder: Decoder): SortedBy {
                        val value = decoder.decodeString()
                        return SortedBy.entries
                            .firstOrNull { it.identifier == value } ?: throw NoSuchElementException()
                    }

                    override fun serialize(
                        encoder: Encoder,
                        value: SortedBy
                    ) {
                        encoder.encodeString(value.identifier)
                    }
                }
            }
        }

        companion object{
            object SinceSerializer : DateTimeSerializer("since")
            object UntilSerializer : DateTimeSerializer("until")
        }
    }



    @Serializable
    data class SearchPostsResponse(
        val cursor: String = "",
        val hitTotal: Int? = null,
        val posts: List<PostView>
    ): AtProtocolModel
}