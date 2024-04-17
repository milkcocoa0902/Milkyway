package com.milkcocoa.info.milkyway.models.bsky.record

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.bsky.record.feed.FeedPostRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.ThreadGateRecord
import com.milkcocoa.info.milkyway.models.bsky.record.graph.BlockRecord
import com.milkcocoa.info.milkyway.models.bsky.record.graph.FollowRecord
import com.milkcocoa.info.milkyway.models.bsky.record.graph.ListBlockRecord
import com.milkcocoa.info.milkyway.models.bsky.record.graph.ListItemRecord
import com.milkcocoa.info.milkyway.models.bsky.record.graph.ListRecord
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable(with = BskyRecord.Companion::class)
abstract class BskyRecord() : Record<RecordType>() {
    companion object : JsonContentPolymorphicSerializer<BskyRecord>(BskyRecord::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<BskyRecord> {
            return when (RecordType.getByIdentifier(element.type)) {
                RecordType.FeedPostRecord -> FeedPostRecord.serializer()
                RecordType.ThreadGateRecord -> ThreadGateRecord.serializer()
                RecordType.FollowRecord -> FollowRecord.serializer()
                RecordType.BlockRecord -> BlockRecord.serializer()
                RecordType.ListRecord -> ListRecord.serializer()
                RecordType.ListItemRecord -> ListItemRecord.serializer()
                RecordType.ListBlockRecord -> ListBlockRecord.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : BskyRecord() {
            override var type = RecordType.UnknownEmbed
        }
    }
}