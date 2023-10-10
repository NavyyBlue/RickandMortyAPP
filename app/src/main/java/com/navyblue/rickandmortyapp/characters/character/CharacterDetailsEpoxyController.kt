package com.navyblue.rickandmortyapp.characters.character

import android.annotation.SuppressLint
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.navyblue.rickandmortyapp.R
import com.navyblue.rickandmortyapp.databinding.ModelCharacterDescriptionBinding
import com.navyblue.rickandmortyapp.databinding.ModelCharacterImageBinding
import com.navyblue.rickandmortyapp.databinding.ModelCharacterNameGenderBinding
import com.navyblue.rickandmortyapp.databinding.ModelEpisodeCarouselItemBinding
import com.navyblue.rickandmortyapp.databinding.ModelTitleBinding
import com.navyblue.rickandmortyapp.domain.models.Character
import com.navyblue.rickandmortyapp.domain.models.Episode
import com.navyblue.rickandmortyapp.epoxy.LoadingEpoxyModel
import com.navyblue.rickandmortyapp.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class CharacterDetailsEpoxyController : EpoxyController() {

    private var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var character: Character? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (character == null) {
            return
        }


        data class ImageEpoxyModel(
            val imageUrl: String
        ) : ViewBindingKotlinModel<ModelCharacterImageBinding>(R.layout.model_character_image) {
            override fun ModelCharacterImageBinding.bind() {
                Picasso.get().load(imageUrl).into(HeaderImageView)
            }
        }

        ImageEpoxyModel(
            imageUrl = character!!.image
        ).id("image").addTo(this)


        data class DetailsCharacterEpoxyModel(
            val name: String,
            val gender: String,
            val status: String,
            val species: String
        ) : ViewBindingKotlinModel<ModelCharacterNameGenderBinding>(R.layout.model_character_name_gender) {
            override fun ModelCharacterNameGenderBinding.bind() {
                nameTextView.text = name
                statusTextView.text = status
                speciesTextView.text = species
                genderImageView.tooltipText= "$gender gender"

                if (gender.equals("male", true))
                    genderImageView.setImageResource(R.drawable.ic_male)
                else genderImageView.setImageResource(R.drawable.ic_female)
            }
        }

        DetailsCharacterEpoxyModel(
            name = character!!.name,
            gender = character!!.gender,
            status = character!!.status,
            species = character!!.species
        ).id("name").addTo(this)


        data class DescriptionEpoxyModel(
            val title: String,
            val description: String
        ) : ViewBindingKotlinModel<ModelCharacterDescriptionBinding>(R.layout.model_character_description) {
            override fun ModelCharacterDescriptionBinding.bind() {
                descriptionTextView.text = description
                descriptionImageView.tooltipText= title

                if (title.equals("Origin", true))
                    descriptionImageView.setImageResource(R.drawable.ic_origin)
                else
                    descriptionImageView.setImageResource(R.drawable.ic_location)
            }

        }

        DescriptionEpoxyModel(
            title = "Origin",
            description = character!!.origin.name
        ).id("origin").addTo(this)

        DescriptionEpoxyModel(
            title = "Last know location",
            description = character!!.location.name
        ).id("location").addTo(this)

        data class TitleEpoxyModel(
            val title: String
        ) : ViewBindingKotlinModel<ModelTitleBinding>(R.layout.model_title) {
            override fun ModelTitleBinding.bind() {
                titleTextView.text = title
            }
        }


        data class EpisodeCarouselItemEpoxyModel(
            val episode: Episode
        ) : ViewBindingKotlinModel<ModelEpisodeCarouselItemBinding>(R.layout.model_episode_carousel_item) {
            @SuppressLint("SetTextI18n")
            override fun ModelEpisodeCarouselItemBinding.bind() {
                episodeTextView.text = episode.getFormattedSeasonTruncated()
                episodeDetailsTextView.text = "${episode.name}\n${episode.airDate}"
            }
        }

        if (character!!.episodeList.isNotEmpty()) {
            TitleEpoxyModel(title = "Episodes").id("title_episodes").addTo(this)
            val items = character!!.episodeList.map {
                EpisodeCarouselItemEpoxyModel(it).id(it.id)
            }
            CarouselModel_()
                .id("episode_carousel")
                .models(items)
                .numViewsToShowOnScreen(1.15f)
                .addTo(this)
        }
    }
}