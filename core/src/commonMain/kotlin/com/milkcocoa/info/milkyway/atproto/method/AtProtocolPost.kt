package com.milkcocoa.info.milkyway.atproto.method

import com.milkcocoa.info.milkyway.atproto.action.Action
import com.milkcocoa.info.milkyway.atproto.models.AtProtocolModel
import com.milkcocoa.info.milkyway.atproto.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.domain.Domain
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

abstract class AtProtocolPost<in I: AtProtocolRequest, out R: AtProtocolModel>(
    private val action: Action,
    private val domain: Domain,
    private val requestClass: KClass<I>,
    private val responseClazz: KClass<R>
): AtProtocolMethod<I, R> {
    @OptIn(InternalSerializationApi::class)
    override suspend fun execute(request: I): R {
        return execute(request, "")
    }

    @OptIn(InternalSerializationApi::class)
    override suspend fun execute(request: I, accessJwt: String): R {
        return withContext(Dispatchers.IO){
            return@withContext HttpClient(CIO).post(
                urlString = "${domain.url}/xrpc/${action.action}"
            ){
                headers {
                    if(accessJwt.isNotBlank()){
                        header(HttpHeaders.Authorization, "Bearer $accessJwt")
                    }
                }
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(requestClass.serializer(), request))
            }.let {
                Json.decodeFromString(
                    responseClazz.serializer(),
                    it.body()
                )
            }
        }
    }
}