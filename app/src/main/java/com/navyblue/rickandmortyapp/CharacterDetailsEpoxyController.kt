package com.navyblue.rickandmortyapp

import com.airbnb.epoxy.EpoxyController
import com.navyblue.rickandmortyapp.databinding.ModelCharacterImageBinding
import com.navyblue.rickandmortyapp.databinding.ModelCharacterLocationBinding
import com.navyblue.rickandmortyapp.databinding.ModelCharacterNameGenderBinding
import com.navyblue.rickandmortyapp.domain.models.Character
import com.navyblue.rickandmortyapp.epoxy.LoadingEpoxyModel
import com.navyblue.rickandmortyapp.epoxy.ViewBindingKotlinModel
import com.navyblue.rickandmortyapp.network.response.GetCharacterByIdResponse
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


        data class NameGenderEpoxyModel(
            val name: String,
            val gender: String,
            val status: String
        ) : ViewBindingKotlinModel<ModelCharacterNameGenderBinding>(R.layout.model_character_name_gender) {
            override fun ModelCharacterNameGenderBinding.bind() {
                nameTextView.text = name
                statusTextView.text = status

                if (gender.equals("male", true))
                    genderImageView.setImageResource(R.drawable.ic_male)
                else genderImageView.setImageResource(R.drawable.ic_female)
            }
        }

        NameGenderEpoxyModel(
            name = character!!.name,
            gender = character!!.gender,
            status = character!!.status
        ).id("name").addTo(this)


        data class LocationEpoxyModel(
            val title: String,
            val description: String
        ) : ViewBindingKotlinModel<ModelCharacterLocationBinding>(R.layout.model_character_location) {
            override fun ModelCharacterLocationBinding.bind() {
                originLabelTextView.text = title
                originTextView.text = description
            }

        }

        LocationEpoxyModel(
            title = "Origin",
            description = character!!.origin.name
        ).id("location").addTo(this)

        LocationEpoxyModel(
            title = "Species",
            description = character!!.species
        ).id("species").addTo(this)

    }
}