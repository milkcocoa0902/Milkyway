package com.milkcocoa.info.milkyway.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Blob(
    val ref: Ref,
    val mimeType: String,
    val size: Int
){
    @SerialName("\$type")
    val type: String = "blob"

    @Serializable
    data class Ref(
        @SerialName("\$link")
        val link: String
    )
}
