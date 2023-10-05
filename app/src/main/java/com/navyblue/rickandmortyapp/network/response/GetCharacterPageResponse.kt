package com.navyblue.rickandmortyapp.network.response

data class GetCharacterPageResponse(
    val info: PageInfo = PageInfo(),
    val results: List<GetCharacterByIdResponse> = emptyList()
)