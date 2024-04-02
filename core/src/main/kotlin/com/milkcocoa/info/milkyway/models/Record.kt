package com.milkcocoa.info.milkyway.models

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class Record<T: SerializableEnum>{
    @SerialName("\$type")
    abstract val type: T
}