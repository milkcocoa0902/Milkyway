package com.milkcocoa.info.milkyway.models

import com.milkcocoa.info.milkyway.util.AtProtoDependencyResolver
import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class Record<T : SerializableEnum> : AtProtoDependencyResolver() {
    @SerialName("\$type")
    abstract val type: T?
}

@Serializable
class AnyRecord(override val type: SerializableEnum? = null) : Record<SerializableEnum>() {
    override fun installDependencies() { }
}