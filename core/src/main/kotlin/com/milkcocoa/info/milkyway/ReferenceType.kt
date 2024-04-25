package com.milkcocoa.info.milkyway

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable
enum class ReferenceType(override val identifier: String) : SerializableEnum {
    StrongRef("com.atproto.repo.strongRef"),
    RepoRef("com.atproto.admin.defs#repoRef"),
    RepoBlobRef("com.atproto.admin.defs#repoBlobRef"),
    Unknown("unknown")
    ;

    companion object {
        fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: Unknown

        object Serializer : SerializableEnum.Companion.SerializableEnumSerializer<ReferenceType>(
            ReferenceType::class
        )
    }
}