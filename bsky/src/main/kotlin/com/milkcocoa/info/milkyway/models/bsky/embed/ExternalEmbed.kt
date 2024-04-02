package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.models.entity.Blob
import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.Serializable

@Serializable
data class ExternalEmbed(
    val external: External
) : Embed(){
    override val type: EmbedType
        get() = EmbedType.EmbedExternal

    @Serializable
    data class External(
        val description: String,
        val thumb: Blob,
        val title: String,
        val uri: String
    )
}