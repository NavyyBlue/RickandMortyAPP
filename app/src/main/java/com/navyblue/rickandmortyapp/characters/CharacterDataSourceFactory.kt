package com.navyblue.rickandmortyapp.characters

import androidx.paging.DataSource
import com.navyblue.rickandmortyapp.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope

class CharacterDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: CharacterRepository
) : DataSource.Factory<Int, GetCharacterByIdResponse>() {

    override fun create(): DataSource<Int, GetCharacterByIdResponse> {
        return CharactersDataSource(coroutineScope, repository)
    }
}