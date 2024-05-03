package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = ActorPreferenceType.Companion.Serializer::class)
enum class ActorPreferenceType(override val identifier: String) : SerializableEnum {
    AdultContentPref("app.bsky.actor.defs#adultContentPref"),
    ContentsLabelPref("app.bsky.actor.defs#contentLabelPref"),
    SavedFeedsPref("app.bsky.actor.defs#savedFeedsPref"),
    PersonalDetailsPref("app.bsky.actor.defs#personalDetailsPref"),
    FeedViewPref("app.bsky.actor.defs#feedViewPref"),
    ThreadViewPref("app.bsky.actor.defs#threadViewPref"),
    InterestsPref("app.bsky.actor.defs#interestsPref"),
    MutedWordsPref("app.bsky.actor.defs#mutedWordsPref"),
    HiddenPostsPref("app.bsky.actor.defs#hiddenPostsPref"),
    Unknown("unknown")
    ;

    companion object {
        fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: Unknown

        object Serializer : SerializableEnum.Companion.SerializableEnumSerializer<ActorPreferenceType>(
            ActorPreferenceType::class
        )
    }
}