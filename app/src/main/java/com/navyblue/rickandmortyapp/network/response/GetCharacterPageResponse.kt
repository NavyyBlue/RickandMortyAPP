package com.navyblue.rickandmortyapp.network.response

import com.navyblue.rickandmortyapp.GetCharacterByIdResponse

data class GetCharacterPageResponse(
    val info: Info = Info(),
    val results: List<GetCharacterByIdResponse> = emptyList()
){
    data class Info(
        val count: Int = 0,
        val pages: Int = 0,
        val next: String? = null,
        val prev: String? = null
    )
}
