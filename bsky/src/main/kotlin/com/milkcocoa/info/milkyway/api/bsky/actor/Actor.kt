package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.domain.Domain

class Actor(
    private val domain: Domain
) {
    suspend fun getProfile(request: GetProfile.GetProfileRequest) = GetProfile(domain).execute(request)
    suspend fun getProfiles(request: GetProfiles.GetProfilesRequest) = GetProfiles(domain).execute(request)
    suspend fun getPReferences(request: GetPreferences.GetPreferencesRequest) = GetPreferences(domain).execute(request)
}