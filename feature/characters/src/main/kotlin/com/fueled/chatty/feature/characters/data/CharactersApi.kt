package com.fueled.chatty.feature.characters.data

import com.fueled.chatty.core.network.data.ApiConstants
import com.fueled.chatty.core.network.data.model.PageApiModel
import com.fueled.chatty.feature.characters.data.model.CharacterApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface for getting characters, character details.
 */
internal interface CharactersApi {

    @GET("characters")
    suspend fun getCharacters(@Query(ApiConstants.Headers.query) heroNameQuery: String?):
        Response<PageApiModel<CharacterApiModel>>

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: Long): Response<PageApiModel<CharacterApiModel>>
}
