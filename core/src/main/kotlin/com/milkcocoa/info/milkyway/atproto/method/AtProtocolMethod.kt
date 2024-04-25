package com.milkcocoa.info.milkyway.atproto.method

import com.milkcocoa.info.milkyway.models.AnyRecord
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.Record
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
                explicitNulls = false
                encodeDefaults = true
                ignoreUnknownKeys = true
                serializersModule +=
                    SerializersModule {
                        polymorphic(Record::class) {
                            defaultDeserializer { AnyRecord.serializer() }
                        }
                    }
                if (KtorHttpClient.getSerializersModules().isEmpty().not()) {
                    serializersModule +=
                        KtorHttpClient.getSerializersModules().reduce {
                                acc,
                                serializersModule ->
                            acc + serializersModule
                        }
                }
            }
}