package com.milkcocoa.info.milkyway.atproto.method

import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest

interface AtProtocolMethod<in I : AtProtocolRequest, out R : AtProtocolModel> {
    suspend fun execute(request: I): R
}