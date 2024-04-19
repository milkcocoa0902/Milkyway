package com.milkcocoa.info.milkyway.atproto.method

import com.milkcocoa.info.milkyway.atproto.action.Action
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.encodeToMap
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

abstract class AtProtocolGet<in I : AtProtocolRequest, out R : AtProtocolModel>(
    private val action: Action,
    private val domain: Domain,
    private val requestClass: KClass<I>,
    private val responseClazz: KClass<R>
) : AtProtocolMethod<I, R> {
    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    override suspend fun execute(request: I): R {
        return withContext(Dispatchers.IO) {
            return@withContext KtorHttpClient.instance().get(
                urlString = "${domain.url}/xrpc/${action.action}"
            ) {
                parameters {
                    Properties.encodeToMap(requestClass.serializer(), request).filterNot {
                        it.key == "accessJwt"
                    }.entries.groupingBy { it.key.split(".").first() }.fold(listOf<String>()) { accumulator, element ->
                        accumulator + element.value.toString()
                    }.forEach {
                        parameter(it.key, it.value.joinToString(","))
                    }
                }
                headers {
                    (request as? AtProtocolRequestWithSession)?.accessJwt.takeIf { it.isNullOrBlank().not() }?.let {
                            accessJwt ->
                        header(HttpHeaders.Authorization, "Bearer $accessJwt")
                    }
                }
                contentType(ContentType.Application.Json)
            }.let {
                println(it.bodyAsText())

                val j =
                    Json {
                        classDiscriminator = "\$type"
                        explicitNulls = true
                        ignoreUnknownKeys = true
                    }

                j.decodeFromString(
                    responseClazz.serializer(),
                    it.body()
                )
            }
        }
    }
}