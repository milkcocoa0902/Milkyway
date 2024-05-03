package com.milkcocoa.info.milkyway.models.error

import com.milkcocoa.info.milkyway.models.AtProtocolModel
import kotlinx.serialization.Serializable


@Serializable
data class AtProtocolError(
  val error: String,
  val message: String
): AtProtocolModel

