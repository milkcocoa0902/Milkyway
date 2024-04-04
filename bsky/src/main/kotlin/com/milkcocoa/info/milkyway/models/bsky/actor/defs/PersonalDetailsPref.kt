package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PersonalDetailsPref(
    @Serializable(with = BirthDataSerializer::class)
    val birthDate: LocalDateTime? = null
) : ActorPreferenceDef() {
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.PersonalDetailsPref

    companion object {
        object BirthDataSerializer : DateTimeSerializer("birthDate")
    }
}