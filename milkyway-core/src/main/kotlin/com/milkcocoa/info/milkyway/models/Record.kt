package com.milkcocoa.info.milkyway.models

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface Record<T : SerializableEnum> {
    @SerialName("\$type")
    abstract val type: T?
}

@Serializable
class AnyRecord(override val type: SerializableEnum? = null) : Record<SerializableEnum>