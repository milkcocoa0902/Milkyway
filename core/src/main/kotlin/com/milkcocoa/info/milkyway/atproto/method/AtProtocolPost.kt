package com.milkcocoa.info.milkyway.atproto.method

import com.milkcocoa.info.milkyway.atproto.action.Action
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolBlobPostRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolUnit
import com.milkcocoa.info.milkyway.models.RefreshUserSession
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.reflect.KClass

abstract class AtProtocolPost<in I : AtProtocolPostRequestModel, out R : AtProtocolModel>(
    private val action: Action,
    private val domain: Domain,
    private val requestClass: KClass<I>,
    private val responseClazz: KClass<R>
) : AtProtocolMethod<I, R> {
    @OptIn(InternalSerializationApi::class, ExperimentalEncodingApi::class, InternalSerializationApi::class)
    override suspend fun execute(request: I): R {
        return withContext(Dispatchers.IO) {
            return@withContext KtorHttpClient.instance().post(
                urlString = "${domain.url}/xrpc/${action.action}"
            ) {
                when (request) {
                    is RequireUserSession -> {
                        headers {
                            request.accessJwt.takeIf { it.isBlank().not() }?.let {
                                    accessJwt ->
                                header(HttpHeaders.Authorization, "Bearer $accessJwt")
                            }
                        }
                        setBody(json.encodeToString(requestClass.serializer(), request))
                    }
                    is RequireAdminSession -> {
                        headers {
                            header(
                                HttpHeaders.Authorization,
                                "Basic ${Base64.encode("admin:${request.adminPassword}".toByteArray())}"
                            )
                        }
                        setBody(json.encodeToString(requestClass.serializer(), request))
                    }
                    is RefreshUserSession -> {
                        headers {
                            header(HttpHeaders.Authorization, "Bearer ${request.refreshJwt}")
                        }
                    }
                    else -> {
                        headers {
                            setBody(json.encodeToString(requestClass.serializer(), request))
                        }
                    }
                }

                contentType(ContentType.Application.Json)
            }.let {
                json.decodeFromString(
                    responseClazz.serializer(),
                    it.body()
                )
            }
        }
    }
}

open class AtProtocolUnitPost<in I : AtProtocolPostRequestModel>(
    action: Action,
    domain: Domain,
    requestClass: KClass<I>
) : AtProtocolPost<I, AtProtocolUnit>(
        action,
        domain,
        requestClass,
        AtProtocolUnit::class
    )

abstract class AtProtocolBlobPost<in I : AtProtocolBlobPostRequestModel, out R : AtProtocolModel>(
    private val action: Action,
    private val domain: Domain,
    private val responseClazz: KClass<R>
) : AtProtocolMethod<I, R> {
    @OptIn(InternalSerializationApi::class, ExperimentalEncodingApi::class)
    override suspend fun execute(request: I): R {
        return withContext(Dispatchers.IO) {
            return@withContext KtorHttpClient.instance().post(
                urlString = "${domain.url}/xrpc/${action.action}"
            ) {
                headers {
                    when (request) {
                        is RequireAdminSession -> {
                            header(
                                HttpHeaders.Authorization,
                                "Basic ${Base64.encode("admin:${request.adminPassword}".toByteArray())}"
                            )
                        }
                        is RequireUserSession -> {
                            request.accessJwt.takeIf { it.isBlank().not() }?.let {
                                    accessJwt ->
                                header(HttpHeaders.Authorization, "Bearer $accessJwt")
                            }
                        }
                    }
                }
                contentType(ContentType.Any)
                setBody(request.binary)
            }.let {
                json.decodeFromString(
                    responseClazz.serializer(),
                    it.body()
                )
            }
        }
    }
}