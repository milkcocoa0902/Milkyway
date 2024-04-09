package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.bsky.feed.Post
import kotlinx.serialization.Serializable

@Serializable
 class ReplyRef(
     val root: Post,
     val parent: Post
)