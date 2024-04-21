package com.milkcocoa.info.milkyway.atproto.method

import com.milkcocoa.info.milkyway.atproto.action.Action
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolBlobRequestWithSession
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.AtProtocolUnit
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
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.modules.plus
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

abstract class AtProtocolPost<in I : AtProtocolRequest, out R : AtProtocolModel>(
    private val action: Action,
    private val domain: Domain,
    private val requestClass: KClass<I>,
    private val responseClazz: KClass<R>
) : AtProtocolMethod<I, R> {
    @OptIn(ExperimentalSerializationApi::class)
    val json =
        Json {
            classDiscriminator = "\$type"
            explicitNulls = true
            ignoreUnknownKeys = true
            encodeDefaults = false

            if (KtorHttpClient.getSerializersModules().isEmpty().not()) {
                serializersModule +=
                    KtorHttpClient.getSerializersModules().reduce {
                            acc,
                            serializersModule ->
                        acc + serializersModule
                    }
            }
        }

    @OptIn(InternalSerializationApi::class)
    override suspend fun execute(request: I): R {
        return withContext(Dispatchers.IO) {
            return@withContext KtorHttpClient.instance().post(
                urlString = "${domain.url}/xrpc/${action.action}"
            ) {
                contentType(ContentType.Application.Json)
                if (request is AtProtocolRequestWithSession) {
                    @OptIn(InternalSerializationApi::class)
                    val s =
                        object : JsonTransformingSerializer<I>(requestClass.serializer()) {
                            override fun transformDeserialize(element: JsonElement): JsonElement {
                                return JsonObject(element.jsonObject.filterKeys { it.equals("accessJwt").not() })
                            }
                        }

                    headers {
                        (request as? AtProtocolRequestWithSession)?.accessJwt.takeIf { it.isNullOrBlank().not() }?.let {
                                accessJwt ->
                            header(HttpHeaders.Authorization, "Bearer $accessJwt")
                        }
                    }
                    setBody(json.encodeToString(s, request).apply { println(this) })
                } else {
                    setBody(json.encodeToString(requestClass.serializer(), request).apply { println(this) })
                }
            }.let {
                println(it.bodyAsText())
                json.decodeFromString(
                    responseClazz.serializer(),
                    it.body()
                )
            }
        }
    }
}

open class AtProtocolUnitPost<in I : AtProtocolRequest>(
    action: Action,
    domain: Domain,
    requestClass: KClass<I>
) : AtProtocolPost<I, AtProtocolUnit>(
        action,
        domain,
        requestClass,
        AtProtocolUnit::class
    )

abstract class AtProtocolBlobPost<in I : AtProtocolBlobRequestWithSession, out R : AtProtocolModel>(
    private val action: Action,
    private val domain: Domain,
    private val responseClazz: KClass<R>
) : AtProtocolMethod<I, R> {
    @OptIn(ExperimentalSerializationApi::class)
    val json =
        Json {
            explicitNulls = false
            encodeDefaults = true
            ignoreUnknownKeys = true
            if (KtorHttpClient.getSerializersModules().isEmpty().not()) {
                serializersModule +=
                    KtorHttpClient.getSerializersModules().reduce {
                            acc,
                            serializersModule ->
                        acc + serializersModule
                    }
            }
        }

    @OptIn(InternalSerializationApi::class)
    override suspend fun execute(request: I): R {
        return withContext(Dispatchers.IO) {
            return@withContext KtorHttpClient.instance().post(
                urlString = "${domain.url}/xrpc/${action.action}"
            ) {
                headers {
                    (request as? AtProtocolRequestWithSession)?.accessJwt.takeIf { it.isNullOrBlank().not() }?.let {
                            accessJwt ->
                        header(HttpHeaders.Authorization, "Bearer $accessJwt")
                    }
                }
                contentType(ContentType.Any)
                setBody(request.binary)
            }.let {
                println(it.bodyAsText())
                json.decodeFromString(
                    responseClazz.serializer(),
                    it.body()
                )
            }
        }
    }
}