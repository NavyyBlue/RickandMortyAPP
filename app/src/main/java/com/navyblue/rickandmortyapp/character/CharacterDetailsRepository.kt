package com.navyblue.rickandmortyapp.character

import com.navyblue.rickandmortyapp.domain.mappers.CharacterMapper
import com.navyblue.rickandmortyapp.domain.models.Character
import com.navyblue.rickandmortyapp.network.CacheApp
import com.navyblue.rickandmortyapp.network.NetworkLayer
import com.navyblue.rickandmortyapp.network.response.GetCharacterByIdResponse
import com.navyblue.rickandmortyapp.network.response.GetEpisodeByIdResponse

class CharacterDetailsRepository {

    suspend fun getCharacterById(characterId: Int): Character? {

        val cacheCharacter = CacheApp.characterMap[characterId]
        if (cacheCharacter!= null){
            return cacheCharacter
        }

        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        val networkEpisode = getEpisodesFromCharacterResponse(request.body)
        val character = CharacterMapper.buildFrom(response = request.body, episodes = networkEpisode)

        CacheApp.characterMap[characterId] = character
        return character
    }

    private suspend fun getEpisodesFromCharacterResponse(
        characterResponse: GetCharacterByIdResponse): List<GetEpisodeByIdResponse> {
        val episodeRange = characterResponse.episode.map {
            it.substring(it.lastIndexOf("/") + 1)
        }.toString()
        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)

        if (request.failed || !request.isSuccessful) {
            return emptyList()
        }

        return request.body
    }
}