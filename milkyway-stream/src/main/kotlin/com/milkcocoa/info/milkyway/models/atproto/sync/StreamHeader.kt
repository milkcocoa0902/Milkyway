package io.github.milkcocoa0902.com.milkcocoa.info.milkyway.models.atproto.sync

import kotlinx.serialization.Serializable

@Serializable
data class StreamHeader(
    val t: String? = null,
    val op: Int
)

enum class StreamHeaderPattern(val hexString: String) {
    COMMIT("a261746723636f6d6d6974626f7001"),
    IDENTITY("a2617469236964656E74697479626f7001"),
    HANDLE("a26174672368616E646C65626f7001"),
    MIGRATE("a2617468236D696772617465626f7001"),
    TOMBSTONE("a261746a23746F6D6273746F6E65626f7001"),
    INFO("a261746523696E666F626f7001"),
    ERROR("a1626f7020")
}