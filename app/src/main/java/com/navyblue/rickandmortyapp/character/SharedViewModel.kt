package com.navyblue.rickandmortyapp.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navyblue.rickandmortyapp.domain.models.Character
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel(){

    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<Character?>()
    val characterByIdLiveData : LiveData<Character?> =  _characterByIdLiveData

    fun refreshCharacter(characterId : Int){
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }
}