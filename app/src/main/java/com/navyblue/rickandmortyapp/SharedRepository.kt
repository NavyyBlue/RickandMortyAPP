package com.navyblue.rickandmortyapp

class SharedRepository {

    suspend fun getCharacterById(characterById : Int): GetCharacterByIdResponse? {
        val request = NetworkLayer.apiClient.getCharacterById(characterById)
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }
}