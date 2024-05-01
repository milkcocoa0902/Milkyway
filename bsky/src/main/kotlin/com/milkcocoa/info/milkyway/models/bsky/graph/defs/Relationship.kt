package com.milkcocoa.info.milkyway.models.bsky.graph.defs

import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.bsky.graph.Relation
import com.milkcocoa.info.milkyway.types.RelationshipType
import kotlinx.serialization.Serializable

/**
 * lists the bi-directional graph relationships between one actor (not indicated in the object), and the target actors (the DID included in the object)
 */
@Serializable
data class Relationship(
    val did: Did,
    /**
     * if the actor follows this DID, this is the AT-URI of the follow record
     */
    val following: String = "",
    /**
     * if the actor is followed by this DID, contains the AT-URI of the follow record
     */
    val followedBy: String = ""
) : Relation() {
    override val type: RelationshipType
        get() = RelationshipType.Relationship
}