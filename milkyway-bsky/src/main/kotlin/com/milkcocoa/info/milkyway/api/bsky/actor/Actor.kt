package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.domain.Domain

class Actor(
    private val domain: Domain
) {
    suspend fun getProfile(request: GetProfile.GetProfileRequest) = GetProfile(domain).execute(request)

    suspend fun getProfiles(request: GetProfiles.GetProfilesRequest) = GetProfiles(domain).execute(request)

    suspend fun getPReferences(request: GetPreferences.GetPreferencesRequest) = GetPreferences(domain).execute(request)

    suspend fun getSuggestions(request: GetSuggestions.GetSuggestionsRequest) = GetSuggestions(domain).execute(request)

    suspend fun putPreferences(request: GetPreferences.GetPreferencesRequest) = GetPreferences(domain).execute(request)

    suspend fun searchActorsTypeahead(request: SearchActorsTypeAhead.SearchActorsTypeAheadRequest) =
        SearchActorsTypeAhead(domain).execute(request)

    suspend fun searchActors(request: SearchActors.SearchActorsRequest) = SearchActors(domain).execute(request)
}