package com.navyblue.rickandmortyapp.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import com.navyblue.rickandmortyapp.Constants
import com.navyblue.rickandmortyapp.domain.models.Episode
import kotlinx.coroutines.flow.map

class EpisodesViewModel : ViewModel(){

    private val repository = EpisodesRepository()
    val flow = Pager(
        PagingConfig(pageSize = Constants.PAGE_SIZE, prefetchDistance = Constants.PREFETCH_DISTANCE, enablePlaceholders = false)
    ){
        EpisodesPagingSource(repository)
    }.flow.cachedIn(viewModelScope).map { 
        it.insertSeparators { episodesUiModel: EpisodesUiModel?, episodesUiModel2: EpisodesUiModel? ->

            if (episodesUiModel == null)
                return@insertSeparators EpisodesUiModel.Header("Season 1")

           if (episodesUiModel == null || episodesUiModel2 == null){
               return@insertSeparators null
           }
            if (episodesUiModel is EpisodesUiModel.Header || episodesUiModel2 is EpisodesUiModel.Header)
                return@insertSeparators null

            val episode1 = (episodesUiModel as EpisodesUiModel.Item).episode
            val episode2 = (episodesUiModel2 as EpisodesUiModel.Item).episode

            return@insertSeparators if (episode2.seasonNumber != episode1.seasonNumber)
                EpisodesUiModel.Header("Season ${episode2.seasonNumber}")
            else{
                null
            }
        }
    }
}