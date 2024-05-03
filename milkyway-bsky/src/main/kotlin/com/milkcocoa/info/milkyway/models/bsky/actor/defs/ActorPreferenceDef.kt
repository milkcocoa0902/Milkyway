package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable(with = ActorPreferenceDef.Companion::class)
abstract class ActorPreferenceDef {
    @SerialName("\$type")
    abstract val type: ActorPreferenceType

    companion object : JsonContentPolymorphicSerializer<ActorPreferenceDef>(ActorPreferenceDef::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<ActorPreferenceDef> {
            return when (ActorPreferenceType.getByIdentifier(element.type)) {
                ActorPreferenceType.AdultContentPref -> AdultContentPref.serializer()
                ActorPreferenceType.ContentsLabelPref -> ContentsLabelPref.serializer()
                ActorPreferenceType.SavedFeedsPref -> SavedFeedsPref.serializer()
                ActorPreferenceType.PersonalDetailsPref -> PersonalDetailsPref.serializer()
                ActorPreferenceType.FeedViewPref -> FeedViewPref.serializer()
                ActorPreferenceType.ThreadViewPref -> ThreadViewPref.serializer()
                ActorPreferenceType.InterestsPref -> InterestsPref.serializer()
                ActorPreferenceType.MutedWordsPref -> MutedWordsPref.serializer()
                ActorPreferenceType.HiddenPostsPref -> HiddenPostsPref.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : ActorPreferenceDef() {
            override var type = ActorPreferenceType.Unknown
        }
    }
}