package com.navyblue.rickandmortyapp.characters

import com.navyblue.rickandmortyapp.network.NetworkLayer
import com.navyblue.rickandmortyapp.network.response.GetCharacterPageResponse

class CharacterRepository {

    suspend fun getCharactersPage(pageIndex : Int): GetCharacterPageResponse? {
        val request = NetworkLayer.apiClient.getCharacterPage(pageIndex)

        if (request.failed || !request.isSuccessful){
            return null
        }
        return request.body
    }
}