package com.milkcocoa.info.milkeyway.atproto.method

import com.milkcocoa.info.milkeyway.atproto.models.AtProtocolModel
import com.milkcocoa.info.milkeyway.atproto.models.AtProtocolRequest

interface AtProtocolMethod<in I: AtProtocolRequest , out R: AtProtocolModel>{
    suspend fun execute(request: I): R
    suspend fun execute(request: I, accessJwt: String): R
}