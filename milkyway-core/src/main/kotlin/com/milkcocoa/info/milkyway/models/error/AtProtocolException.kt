package com.milkcocoa.info.milkyway.models.error

import com.milkcocoa.info.milkyway.action.Action

class AtProtocolException(
    val action: Action,
    val error: AtProtocolError?,
    val wrapped: Throwable?
): RuntimeException(action.action)