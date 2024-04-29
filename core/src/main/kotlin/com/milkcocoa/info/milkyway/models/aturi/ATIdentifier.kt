package com.milkcocoa.info.milkyway.models.aturi

import kotlinx.serialization.Serializable

@Serializable
sealed class ATIdentifier {
    @Serializable
    enum class IdentifierType {
        Handle,
        Did
    }

    abstract val type: IdentifierType
}