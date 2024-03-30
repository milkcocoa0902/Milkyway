package com.milkcocoa.info.milkyway.atproto.method

import com.milkcocoa.info.milkyway.atproto.action.Action
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.util.httpClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.serializersModule
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

abstract class AtProtocolGet<in I: AtProtocolRequest, out R: AtProtocolModel>(
    private val action: Action,
    private val domain: Domain,
    private val requestClass: KClass<I>,
    private val responseClazz: KClass<R>
): AtProtocolMethod<I, R> {

    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    override suspend fun execute(request: I): R {


        return withContext(Dispatchers.IO){
            return@withContext httpClient.instance().get(
                urlString = "${domain.url}/xrpc/${action.action}"
            ){
                parameters {
                    Properties.encodeToMap(requestClass.serializer(), request).forEach {
                        parameter(it.key, it.value)
                    }
                }
                headers {
                    (request as? AtProtocolRequestWithSession)?.accessJwt.takeIf { it.isNullOrBlank().not() }?.let { accessJwt ->
                        header(HttpHeaders.Authorization, "Bearer $accessJwt")
                    }
                }
                contentType(ContentType.Application.Json)
            }.let {
                println(it.bodyAsText())

                val j = Json {
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