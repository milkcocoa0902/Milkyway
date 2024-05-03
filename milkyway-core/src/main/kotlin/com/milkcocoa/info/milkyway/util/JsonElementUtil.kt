package com.milkcocoa.info.milkyway.util

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object JsonElementUtil {
    val JsonElement.type get() = this.jsonObject["\$type"]?.jsonPrimitive?.contentOrNull
}