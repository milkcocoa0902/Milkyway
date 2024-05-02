package com.milkcocoa.info.milkyway.atproto.method

import com.milkcocoa.info.milkyway.atproto.action.Action
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.encodeToMap
import kotlinx.serialization.serializer
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.reflect.KClass

abstract class AtProtocolGet<in I : AtProtocolGetRequestModel, out R : AtProtocolModel>(
    private val action: Action,
    private val domain: Domain,
    private val requestClass: KClass<I>,
    private val responseClazz: KClass<R>
) : AtProtocolMethod<I, R> {
    @OptIn(InternalSerializationApi::class, ExperimentalEncodingApi::class, ExperimentalSerializationApi::class)
    override suspend fun execute(request: I): R {
        return withContext(Dispatchers.IO) {
            return@withContext KtorHttpClient.instance().get(
                urlString = "${domain.url}/xrpc/${action.action}"
            ) {
                parameters {
                    Properties.encodeToMap(requestClass.serializer(), request).filterKeys {
                        /**
                         * not a credential
                         */
                        it != "accessJwt" && it != "adminPassword" &&
                            /**
                             * example [com.milkcocoa.info.milkyway.models.aturi.Did] serialize into properties result
                             * actor="did:plc:asdfre87fvbevavev", actor.type="Did"
                             * so remove actor.type
                             *
                             * list of above,
                             * actor.0="did:plc:ads89vayktajyv9aa", actor.0.type="Did", actor.1.type=.......
                             * so remove actor.<number>.type
                             */
                            (it.contains(".").not() || it.split(".").last().toIntOrNull() != null)
                    }.entries.groupingBy { it.key.split(".").first() }
                        .fold(listOf<String>()) { accumulator, element ->
                            accumulator + element.value.toString()
                        }.forEach {
                            parameter(it.key, it.value.joinToString(","))
                        }
                }

                when (request) {
                    is RequireUserSession -> {
                        headers {
                            request.accessJwt.takeIf { it.isBlank().not() }?.let {
                                    accessJwt ->
                                header(HttpHeaders.Authorization, "Bearer $accessJwt")
                            }
                        }
                    }
                    is RequireAdminSession -> {
                        headers {
                            header(
                                HttpHeaders.Authorization,
                                "Basic ${Base64.encode("admin:${request.adminPassword}".toByteArray())}"
                            )
                        }
                    }
                    else -> { }
                }
            }.let {
                json.decodeFromString(
                    responseClazz.serializer(),
                    it.body()
                )
            }
        }
    }
}