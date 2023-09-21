package com.navyblue.rickandmortyapp.domain.mappers

import com.navyblue.rickandmortyapp.domain.models.Character
import com.navyblue.rickandmortyapp.network.response.GetCharacterByIdResponse
import com.navyblue.rickandmortyapp.network.response.GetCharacterPageResponse

object CharacterMapper {
    fun buildFrom(response: GetCharacterByIdResponse): Character{
        return  Character(
            episodeList = emptyList(),
            gender = response.gender,
            id = response.id,
            image = response.image,
            location = Character.Location(
                name = response.location.name,
                url = response.location.url
            ),
            name = response.name,
            origin = Character.Origin(
                name = response.origin.name,
                url = response.origin.url
            ),
            species = response.species,
            status = response.status,
            created = response.created
        )
    }
}