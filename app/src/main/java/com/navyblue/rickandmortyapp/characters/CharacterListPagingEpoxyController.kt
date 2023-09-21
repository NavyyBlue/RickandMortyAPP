package com.navyblue.rickandmortyapp.characters

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.navyblue.rickandmortyapp.GetCharacterByIdResponse
import com.navyblue.rickandmortyapp.R
import com.navyblue.rickandmortyapp.databinding.ModelCharacterListItemBinding
import com.navyblue.rickandmortyapp.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class CharacterListPagingEpoxyController : PagedListEpoxyController<GetCharacterByIdResponse>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(item!!.image, item.name).id(item.id)
    }


    data class CharacterGridItemEpoxyModel(
        val imageUrl: String,
        val name: String
    ): ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item){
        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(imageViewCharacter)
            textViewNameCharacter.text=name
        }
    }
}