package com.navyblue.rickandmortyapp.episodes.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navyblue.rickandmortyapp.domain.models.Episode
import com.navyblue.rickandmortyapp.episodes.EpisodesRepository
import kotlinx.coroutines.launch

class EpisodeDetailsViewModel : ViewModel(){
    private val repository = EpisodesRepository()
    private var _episodeLiveData = MutableLiveData<Episode?>()

    val episodeLiveData: LiveData<Episode?> =_episodeLiveData

    fun refreshEpisode(episodeId: Int){
        viewModelScope.launch {
            val episode = repository.getEpisodeById(episodeId)
            _episodeLiveData.postValue(episode)
        }
    }
}