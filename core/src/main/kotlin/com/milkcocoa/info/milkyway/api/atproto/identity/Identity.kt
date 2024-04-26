package com.milkcocoa.info.milkyway.api.atproto.identity

import com.milkcocoa.info.milkyway.domain.Domain

class Identity(val domain: Domain) {
    suspend fun getRecommendedDidCredentials(request: GetRecommendedDidCredentials.GetRecommendedDidCredentialsRequest)
        = GetRecommendedDidCredentials(domain).execute(request)

    suspend fun requestPlcOperationSignature(request: RequestPlcOperationSignature.RequestPlcOperationSignatureRequest)
        = RequestPlcOperationSignature(domain).execute(request)

    suspend fun resolveHandle(request: ResolveHandle.ResolveHandleRequest)
        = ResolveHandle(domain).execute(request)

    suspend fun signPlcOperation(request: SignPlcOperation.SignPlcOperationRequest)
        = SignPlcOperation(domain).execute(request)

    suspend fun submitPlcOperation(request: SubmitPlcOperation.SubmitPlcOperationRequest)
        = SubmitPlcOperation(domain).execute(request)

    suspend fun updateHandle(request: UpdateHandle.UpdateHandleRequest)
        = UpdateHandle(domain).execute(request)
}