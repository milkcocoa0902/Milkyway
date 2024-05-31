package com.milkcocoa.info.milkyway.http

import com.milkcocoa.info.milkyway.models.AnyRecord
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.entity.BlobBase
import com.milkcocoa.info.milkyway.models.entity.LegacyBlobObject
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import kotlinx.serialization.modules.polymorphic

interface AtProtocolMethod<in I : AtProtocolRequest, out R : AtProtocolModel> {
    suspend fun execute(request: I): R

    @OptIn(ExperimentalSerializationApi::class)
    val json: Json
        get() =
            Json {
                classDiscriminator = "\$type"
                explicitNulls = false
                ignoreUnknownKeys = true
                serializersModule +=
                    SerializersModule {
                        polymorphic(Record::class) {
                            defaultDeserializer { AnyRecord.serializer() }
                        }

                        polymorphic(BlobBase::class) {
                            defaultDeserializer { LegacyBlobObject.serializer() }
                        }
                    }
                KtorHttpClient.getSerializersModules()
                    .takeIf { it.isNotEmpty() }
                    ?.let { serializerModules ->
                        serializerModules.reduce { a, b -> a + b }
                    }?.run {
                        serializersModule += this
                    }
            }
}