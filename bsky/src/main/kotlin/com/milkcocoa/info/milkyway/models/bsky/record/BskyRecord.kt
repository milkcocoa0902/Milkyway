package com.milkcocoa.info.milkyway.models.bsky.record

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.bsky.feed.FeedPostRecord
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable(with = BskyRecord.Companion::class)
abstract class BskyRecord(): Record<RecordType>(){

    companion object : JsonContentPolymorphicSerializer<BskyRecord>(BskyRecord::class){
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<BskyRecord> {
            return when(RecordType.getByIdentifier(element.type)){
                RecordType.FeedPostRecord -> FeedPostRecord.serializer()
                else -> Unknown.serializer()
            }
        }
        @Serializable
        class Unknown : BskyRecord() {
            override var type = RecordType.UnknownEmbed
        }
    }
}