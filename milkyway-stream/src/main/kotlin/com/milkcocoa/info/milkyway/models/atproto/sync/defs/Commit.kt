package io.github.milkcocoa0902.com.milkcocoa.info.milkyway.models.atproto.sync.defs

import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import io.github.milkcocoa0902.com.milkcocoa.info.milkyway.models.atproto.sync.StreamMessage
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.cbor.ByteString
import java.time.LocalDateTime

@Serializable
data class Commit
    @OptIn(ExperimentalSerializationApi::class)
    constructor(
        /**
         * The stream sequence number of this message.
         */
        val seq: Int,
        /**
         * DEPRECATED -- unused
         */
        @Deprecated(message = "the field is deprecated", level = DeprecationLevel.HIDDEN)
        val rebase: Boolean,
        /**
         * Indicates that this commit contained too many ops, or data size was too large. Consumers will need to make a separate request to get missing data.
         */
        val tooBig: Boolean,
        /**
         *The repo this event comes from.
         */
        val repo: Did,
        /**
         * Repo commit object CID.
         */
        @ByteString
        val commit: ByteArray,
        /**
         * DEPRECATED -- unused. WARNING -- nullable and optional; stick with optional to ensure golang interoperability.
         */
        @Deprecated(message = "the field is deprecated", level = DeprecationLevel.HIDDEN)
        @ByteString
        val prev: ByteArray? = null,
        /**
         * The rev of the emitted commit. Note that this information is also in the commit object included in blocks, unless this is a tooBig event.
         */
        val rev: String,
        /**
         * The rev of the last emitted commit from this repo (if any).
         */
        val since: String?,
        /**
         * CAR file containing relevant blocks, as a diff since the previous repo state.
         */
        @ByteString
        val blocks: ByteArray,
        /**
         * List of repo mutation operations in this commit (eg, records created, updated, or deleted).
         */
        val ops: List<RepoOperation>,
        /**
         * List of new blobs (by CID) referenced by records in this commit.
         */
        @ByteString
        val blobs: List<ByteArray>,
        /**
         * Timestamp of when this message was originally broadcast.
         */
        @Serializable(with = DateTimeSerializer::class)
        val time: LocalDateTime
    ) : StreamMessage