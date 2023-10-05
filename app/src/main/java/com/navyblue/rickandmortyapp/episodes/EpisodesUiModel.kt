package com.navyblue.rickandmortyapp.episodes

import com.navyblue.rickandmortyapp.domain.models.Episode

sealed class EpisodesUiModel {
    class Item(val episode: Episode) : EpisodesUiModel()
    class Header(val text: String) : EpisodesUiModel()
}
