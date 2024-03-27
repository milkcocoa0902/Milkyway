package com.milkcocoa.info.milkyway.atproto.method

import com.milkcocoa.info.milkyway.atproto.models.AtProtocolModel
import com.milkcocoa.info.milkyway.atproto.models.AtProtocolRequest

interface AtProtocolMethod<in I: AtProtocolRequest , out R: AtProtocolModel>{
    suspend fun execute(request: I): R
    suspend fun execute(request: I, accessJwt: String): R
}