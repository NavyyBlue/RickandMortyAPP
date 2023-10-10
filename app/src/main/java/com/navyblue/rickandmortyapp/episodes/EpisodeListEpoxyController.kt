package com.navyblue.rickandmortyapp.episodes

import android.annotation.SuppressLint
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.navyblue.rickandmortyapp.R
import com.navyblue.rickandmortyapp.databinding.ModelEpisodeListItemBinding
import com.navyblue.rickandmortyapp.databinding.ModelEpisodeListTitleBinding
import com.navyblue.rickandmortyapp.domain.models.Episode
import com.navyblue.rickandmortyapp.epoxy.ViewBindingKotlinModel

class EpisodeListEpoxyController( private val onEpisodeClicked: (Int) -> Unit): PagingDataEpoxyController<EpisodesUiModel>() {
    override fun buildItemModel(currentPosition: Int, item: EpisodesUiModel?): EpoxyModel<*> {
        return when(item!!){
            is EpisodesUiModel.Item->{
                val episode = (item as EpisodesUiModel.Item).episode
                EpisodeListItemEpoxyModel(
                    episode = episode,
                    onClick = { episodeId->
                        onEpisodeClicked(episodeId)
                    }
                ).id("episode_${episode.id}")
            }
            is EpisodesUiModel.Header->{
                val headerText = (item as EpisodesUiModel.Header).text
                EpisodeListTitleEpoxyModel(headerText).id("header_${headerText}")
            }
        }
    }

    data class EpisodeListItemEpoxyModel(
        val episode: Episode,
        val onClick: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelEpisodeListItemBinding>(R.layout.model_episode_list_item) {
        @SuppressLint("SetTextI18n")
        override fun ModelEpisodeListItemBinding.bind() {
            episodeDetailsTextView.text = "${episode.name}\n${episode.airDate}"
            episodeTextView.text = episode.getFormattedSeasonTruncated()

            root.setOnClickListener { onClick(episode.id) }
        }
    }

    data class EpisodeListTitleEpoxyModel(
        val title: String): ViewBindingKotlinModel<ModelEpisodeListTitleBinding>(R.layout.model_episode_list_title){
        override fun ModelEpisodeListTitleBinding.bind() {
            titleTextView.text= title
        }

    }
}