package com.navyblue.rickandmortyapp.episodes.episode

import com.airbnb.epoxy.EpoxyController
import com.navyblue.rickandmortyapp.R
import com.navyblue.rickandmortyapp.databinding.ModelCharacterListItemCardBinding
import com.navyblue.rickandmortyapp.domain.models.Character
import com.navyblue.rickandmortyapp.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class EpisodeDetailsEpoxyController(val characterList: List<Character>) : EpoxyController(){
    override fun buildModels() {
        characterList.forEach {character ->
            CharacterEpoxyModel(
                imageUrl = character.image,
                name = character.name
            ).id(character.id).addTo(this)
        }
    }

    data class CharacterEpoxyModel(
        val imageUrl: String,
        val name: String
    ): ViewBindingKotlinModel<ModelCharacterListItemCardBinding>(
        R.layout.model_character_list_item_card
    ){
        override fun ModelCharacterListItemCardBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name
        }

    }
}