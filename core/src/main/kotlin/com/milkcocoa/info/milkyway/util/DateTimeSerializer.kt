package com.milkcocoa.info.milkyway.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME
import java.time.format.DateTimeFormatterBuilder

open class DateTimeSerializer(private val serialName: String) : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor get() = PrimitiveSerialDescriptor(serialName, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDateTime {
        return LocalDateTime.parse(
            decoder.decodeString(),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]ZZZZZ")
        )
            .atZone(ZoneOffset.UTC)
            .withZoneSameInstant(ZoneId.systemDefault())
            .toLocalDateTime()
    }

    override fun serialize(
        encoder: Encoder,
        value: LocalDateTime
    ) {
        value.atZone(ZoneId.systemDefault())
            .withZoneSameInstant(ZoneOffset.UTC)
            .toLocalDateTime()
            .also {
                encoder.encodeString(it.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
            }
    }
}