package com.milkcocoa.info.milkyway.models.entity

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

class BlobObjectTest {
    @Test
    fun test_001() {
        val t = "\$type"
        val l = "\$link"

        val j =
            """
            {
                "$t": "blob",
                "ref": {
                    "$l": "blob-cid-link"
                },
                "mimeType": "application/octet-stream",
                "size": 1345
            }
            """.trimIndent()

        Assertions.assertDoesNotThrow {
            Json {
                ignoreUnknownKeys = true
                classDiscriminator = "\$type"
            }.decodeFromString<BlobObject>(j)
        }
    }

    @Test
    fun test_002() {
        val j =
            """
            {
                "cid": "awv7rehvbrnvr",
                "mimeType": "application/octet-stream"
            }
            """.trimIndent()

        Assertions.assertDoesNotThrow {
            Json {
                ignoreUnknownKeys = true
                classDiscriminator = "\$type"
            }.decodeFromString<LegacyBlobObject>(j)
        }
    }

    @Test
    fun test_003() {
        val t = "\$type"
        val l = "\$link"

        val j =
            """
            {
                "$t": "blob",
                "ref": {
                    "$l": "blob-cid-link"
                },
                "mimeType": "application/octet-stream",
                "size": 1345
            }
            """.trimIndent()

        Assertions.assertDoesNotThrow {
            Json {
                ignoreUnknownKeys = true
                classDiscriminator = "\$type"
            }.decodeFromString<BlobBase>(j)
        }
    }

    @Test
    fun test_004() {
        val j =
            """
            {
                "cid": "awv7rehvbrnvr",
                "mimeType": "application/octet-stream"
            }
            """.trimIndent()

        Assertions.assertDoesNotThrow {
            Json {
                ignoreUnknownKeys = true
                classDiscriminator = "\$type"
                serializersModule =
                    SerializersModule {
                        polymorphic(BlobBase::class) {
                            defaultDeserializer { LegacyBlobObject.serializer() }
                        }
                    }
            }.decodeFromString<BlobBase>(j)
        }
    }
}