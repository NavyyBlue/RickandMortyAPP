package com.navyblue.rickandmortyapp

import retrofit2.Response

class ApiClient(
    private val rickAndMortyService: RickAndMortyService
) {
    suspend fun getCharacterById(characterById : Int): Response<GetCharacterByIdResponse> {
        return rickAndMortyService.getCharacterById(characterById)
    }
}