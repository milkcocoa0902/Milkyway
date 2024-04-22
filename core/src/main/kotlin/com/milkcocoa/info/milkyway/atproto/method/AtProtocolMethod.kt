package com.milkcocoa.info.milkyway.atproto.method

import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.plus

interface AtProtocolMethod<in I : AtProtocolRequest, out R : AtProtocolModel> {
    suspend fun execute(request: I): R

    @OptIn(ExperimentalSerializationApi::class)
    val json: Json
        get() = Json {
            explicitNulls = false
            encodeDefaults = true
            ignoreUnknownKeys = true
            if (KtorHttpClient.getSerializersModules().isEmpty().not()) {
                serializersModule +=
                    KtorHttpClient.getSerializersModules().reduce { acc,
                                                                    serializersModule ->
                        acc + serializersModule
                    }
            }
        }
}