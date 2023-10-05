package com.navyblue.rickandmortyapp.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navyblue.rickandmortyapp.domain.models.Character
import com.navyblue.rickandmortyapp.network.CacheApp
import kotlinx.coroutines.launch

class CharacterDetailsViewModel : ViewModel(){

    private val repository = CharacterDetailsRepository()

    private val _characterByIdLiveData = MutableLiveData<Character?>()
    val characterByIdLiveData : LiveData<Character?> =  _characterByIdLiveData

    fun refreshCharacter(characterId : Int){



        viewModelScope.launch {
            val character = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(character)


            character?.let { CacheApp.characterMap[characterId] = it }
        }
    }
}