package com.milkcocoa.info.milkyway.models

import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable
abstract class Record<T : SerializableEnum> {
    @SerialName("\$type")
    abstract val type: T
}