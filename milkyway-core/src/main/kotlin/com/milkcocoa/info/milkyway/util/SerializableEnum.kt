package com.milkcocoa.info.milkyway.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.reflect.KClass

interface SerializableEnum {
    val identifier: String

    companion object {
        open class SerializableEnumSerializer<T : Enum<T>>(private val clazz: KClass<T>) : KSerializer<T> {
            override val descriptor: SerialDescriptor get() = PrimitiveSerialDescriptor("\$type", PrimitiveKind.STRING)

            override fun deserialize(decoder: Decoder): T {
                val value = decoder.decodeString()
                return clazz.java.enumConstants
                    .firstOrNull { (it as SerializableEnum).identifier == value } ?: throw NoSuchElementException()
            }

            override fun serialize(
                encoder: Encoder,
                value: T
            ) {
                when (val label = (value as SerializableEnum).identifier) {
                    null -> throw NoSuchElementException()
                    else -> encoder.encodeString(label)
                }
            }
        }
    }
}