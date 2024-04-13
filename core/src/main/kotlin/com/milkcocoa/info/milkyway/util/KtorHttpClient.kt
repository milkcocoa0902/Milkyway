package com.milkcocoa.info.milkyway.util

import io.ktor.client.*
import io.ktor.client.engine.cio.*

object KtorHttpClient {
    fun instance() =
        HttpClient(CIO) {
            engine {

            }
        }
}