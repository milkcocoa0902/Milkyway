package com.milkcocoa.info.milkyway.util

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*

object httpClient {
    fun instance() = HttpClient(CIO){

    }
}