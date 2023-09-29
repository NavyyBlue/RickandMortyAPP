package com.navyblue.rickandmortyapp.character

import com.navyblue.rickandmortyapp.domain.mappers.CharacterMapper
import com.navyblue.rickandmortyapp.domain.models.Character
import com.navyblue.rickandmortyapp.network.NetworkLayer
import com.navyblue.rickandmortyapp.network.response.GetCharacterByIdResponse
import com.navyblue.rickandmortyapp.network.response.GetEpisodeByIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterById: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterById)

        if (request.failed) {
            return null
        }
        if (!request.isSuccessful) {
            return null
        }

        val networkEpisode = getEpisodesFromCharacterResponse(request.body)
        return CharacterMapper.buildFrom(response = request.body, episodes = networkEpisode)
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