package com.navyblue.rickandmortyapp.network

import com.navyblue.rickandmortyapp.network.response.GetCharacterByIdResponse
import com.navyblue.rickandmortyapp.network.response.GetCharacterPageResponse
import com.navyblue.rickandmortyapp.network.response.GetEpisodeByIdResponse
import com.navyblue.rickandmortyapp.network.response.GetEpisodePageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {
    @GET("character/{character-id}")
    suspend fun getCharacterById(@Path("character-id") characterId: Int): Response<GetCharacterByIdResponse>

    @GET("character/{list}")
    suspend fun getMultipleCharacters(@Path("list") characterList: List<String>): Response<List<GetCharacterByIdResponse>>

    @GET("character")
    suspend fun getCharactersPage(@Query("page") pageIndex: Int): Response<GetCharacterPageResponse>

    @GET("episode/{episode-id}")
    suspend fun getEpisodeById(@Path("episode-id") episodeId: Int): Response<GetEpisodeByIdResponse>

    @GET("episode/{episode-range}")
    suspend fun getEpisodeRange(@Path("episode-range") episodeRange: String): Response<List<GetEpisodeByIdResponse>>

    @GET("episode/")
    suspend fun getEpisodePage(@Query("page") pageIndex: Int): Response<GetEpisodePageResponse>
}