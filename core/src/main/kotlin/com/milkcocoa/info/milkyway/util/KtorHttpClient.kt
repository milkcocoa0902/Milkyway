package com.milkcocoa.info.milkyway.util

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.serialization.modules.SerializersModule

object KtorHttpClient {
    private var serializerModules = emptyList<SerializersModule>()
    fun addSerializersModule(module: SerializersModule) {
        serializerModules = serializerModules + module
    }

    fun getSerializersModules() = serializerModules

    fun instance() =
        HttpClient(CIO) {
            engine {
            }
        }
}