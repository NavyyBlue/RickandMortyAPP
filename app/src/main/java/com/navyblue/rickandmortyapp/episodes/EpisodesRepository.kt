package com.navyblue.rickandmortyapp.episodes

import com.navyblue.rickandmortyapp.domain.mappers.EpisodeMapper
import com.navyblue.rickandmortyapp.domain.models.Episode
import com.navyblue.rickandmortyapp.network.NetworkLayer
import com.navyblue.rickandmortyapp.network.response.GetCharacterByIdResponse
import com.navyblue.rickandmortyapp.network.response.GetEpisodeByIdResponse
import com.navyblue.rickandmortyapp.network.response.GetEpisodePageResponse

class EpisodesRepository {
    suspend fun fetchEpisodePage(pageIndex: Int): GetEpisodePageResponse?{
        val pageRequest = NetworkLayer.apiClient.getEpisodePage(pageIndex)

        if (!pageRequest.isSuccessful){
            return null
        }
        return pageRequest.body
    }

    suspend fun getEpisodeById(episodeId: Int): Episode?{
        val request = NetworkLayer.apiClient.getEpisodeById(episodeId = episodeId)
        if (!request.isSuccessful){
            return null
        }

        val characterList = getcharacterFromEpisodeResponse(request.body)
        return EpisodeMapper.buildFrom(
            networkEpisode = request.body,
            networkCharacters = characterList
        )
    }

    private suspend fun getcharacterFromEpisodeResponse(
        episodeByIdResponse: GetEpisodeByIdResponse
    ):List<GetCharacterByIdResponse>{
        val characterList = episodeByIdResponse.characters.map{
            it.substring(startIndex = it.lastIndexOf("/")+1)
        }
        val request = NetworkLayer.apiClient.getMultipleCharacters(characterList)
        return request.bodyNullable ?: emptyList()
    }

}