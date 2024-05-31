package io.github.milkcocoa0902.com.milkcocoa.info.milkyway.models.atproto.sync.defs

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.cbor.ByteString

/**
 * A repo operation, ie a mutation of a single record.
 */
@Serializable
data class RepoOperation
    @OptIn(ExperimentalSerializationApi::class)
    constructor(
        val action: OperationAction,
        val path: String,
        /**
         * For creates and updates, the new record CID. For deletions, null.
         */
        @ByteString
        val cid: ByteArray
    ) {
        @Serializable
        enum class OperationAction {
            @SerialName("create")
            Create,

            @SerialName("update")
            Update,

            @SerialName("delete")
            Delete
        }
    }