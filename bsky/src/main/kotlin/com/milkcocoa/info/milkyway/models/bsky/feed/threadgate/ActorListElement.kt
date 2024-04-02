package com.milkcocoa.info.milkyway.models.bsky.feed.threadgate

import com.milkcocoa.info.milkyway.models.entity.Label
import kotlinx.serialization.Serializable

@Serializable
data class ActorListElement(
    val uri: String,
    val cid: String,
    val name: String,
    val purpose: String,
    val avatar: String,
    val labels: List<Label> = emptyList(),
    val viewer: Viewer,
    val indexedAt: String
){
    @Serializable
    data class Viewer(
        val muted: Boolean? = null,
        val blocked: String? = null
    )
}