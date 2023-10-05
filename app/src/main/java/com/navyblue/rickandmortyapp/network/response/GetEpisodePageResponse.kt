package com.navyblue.rickandmortyapp.network.response

data class GetEpisodePageResponse(
    val info: PageInfo = PageInfo(),
    val results: List<GetEpisodeByIdResponse> = emptyList()
)