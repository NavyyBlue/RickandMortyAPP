package com.navyblue.rickandmortyapp

import com.navyblue.rickandmortyapp.network.NetworkLayer
import com.navyblue.rickandmortyapp.network.response.GetCharacterByIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterById : Int): GetCharacterByIdResponse? {
        val request = NetworkLayer.apiClient.getCharacterById(characterById)

        if (request.failed){
            return null
        }
        if(!request.isSuccessful){
            return null
        }
        return request.body
    }
}