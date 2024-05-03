package com.milkcocoa.info.milkyway.models.bsky.graph.defs

import com.milkcocoa.info.milkyway.models.bsky.graph.Relation
import com.milkcocoa.info.milkyway.types.RelationshipType
import kotlinx.serialization.Serializable

/**
 * indicates that a handle or DID could not be resolved
 */
@Serializable
data class NotFoundActor(
    val actor: String,
    val notFound: Boolean = true
) : Relation() {
    override val type: RelationshipType
        get() = RelationshipType.NotFoundActor
}