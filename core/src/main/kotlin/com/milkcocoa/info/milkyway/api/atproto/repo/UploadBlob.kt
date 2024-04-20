package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolBlobPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolBlobRequestWithSession
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.entity.BlobObject
import kotlinx.serialization.Serializable

/**
 * Upload a new blob, to be referenced from a repository record.
 * The blob will be deleted if it is not referenced within a time window (eg, minutes).
 * Blob restrictions (mimetype, size, etc) are enforced when the reference is created.
 * Requires auth, implemented by PDS.
 */
class UploadBlob(val domain: Domain) :
    AtProtocolBlobPost<UploadBlob.UploadBlobRequest, UploadBlob.UploadBlobResponse>(
        AtProtoActions.UploadBlob,
        domain,
        UploadBlobResponse::class
    ) {
    @Serializable
    data class UploadBlobRequest(
        override val accessJwt: String,
        override val binary: ByteArray
    ) : AtProtocolBlobRequestWithSession

    @Serializable
    data class UploadBlobResponse(
        val blob: BlobObject
    ): AtProtocolModel
}