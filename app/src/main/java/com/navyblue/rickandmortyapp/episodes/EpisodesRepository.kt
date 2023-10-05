package com.navyblue.rickandmortyapp.episodes

import com.navyblue.rickandmortyapp.network.NetworkLayer
import com.navyblue.rickandmortyapp.network.response.GetEpisodePageResponse

class EpisodesRepository {
    suspend fun fetchEpisodePage(pageIndex: Int): GetEpisodePageResponse?{
        val pageRequest = NetworkLayer.apiClient.getEpisodePage(pageIndex)

        if (!pageRequest.isSuccessful){
            return null
        }
        return pageRequest.body
    }
}