package com.milkcocoa.info.milkyway.models.bsky.feed

import com.milkcocoa.info.milkyway.models.bsky.embed.view.EmbedView
import com.milkcocoa.info.milkyway.models.bsky.embed.view.ExternalEmbedView
import com.milkcocoa.info.milkyway.models.bsky.embed.view.ImageEmbedView
import com.milkcocoa.info.milkyway.models.bsky.embed.view.RecordEmbedView
import com.milkcocoa.info.milkyway.models.bsky.embed.view.RecordWithMediaEmbedView
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.BlockedPost
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.NotFoundPost
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.PostView
import com.milkcocoa.info.milkyway.types.EmbedViewType
import com.milkcocoa.info.milkyway.types.PostType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable(with = Post.Companion::class)
abstract class Post {
    @SerialName("\$type")
    abstract val type: PostType

    companion object : JsonContentPolymorphicSerializer<Post>(Post::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Post> {
            return when (PostType.getByIdentifier(element.type)) {
                PostType.Post -> PostView.serializer()
                PostType.NotFoundPost -> NotFoundPost.serializer()
                PostType.BlockedPost -> BlockedPost.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : Post() {
            override var type = PostType.Unknown
        }
    }
}