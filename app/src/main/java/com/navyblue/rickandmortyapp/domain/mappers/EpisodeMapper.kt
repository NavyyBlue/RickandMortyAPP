package com.navyblue.rickandmortyapp.domain.mappers

import com.navyblue.rickandmortyapp.domain.models.Episode
import com.navyblue.rickandmortyapp.network.response.GetEpisodeByIdResponse

object EpisodeMapper {
    fun buildFrom(networkEpisode : GetEpisodeByIdResponse): Episode{
        return Episode(
            id = networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.air_date,
            episode = networkEpisode.episode
        )
    }
}