package com.milkcocoa.info.milkyway.models.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class BlobBase {
    @SerialName("\$type")
    abstract val type: String
}

@Serializable
@SerialName("blob")
data class BlobObject(
    val ref: Ref,
    val mimeType: String,
    val size: Int
) : BlobBase() {
    override val type: String
        get() = "blob"

    @Serializable
    data class Ref(
        @SerialName("\$link")
        val link: String
    )
}