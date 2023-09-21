package com.navyblue.rickandmortyapp.epoxy

import com.navyblue.rickandmortyapp.R
import com.navyblue.rickandmortyapp.databinding.ModelLoadingBinding

class LoadingEpoxyModel : ViewBindingKotlinModel<ModelLoadingBinding>(R.layout.model_loading){
    override fun ModelLoadingBinding.bind() {
        //no hace nada
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

}