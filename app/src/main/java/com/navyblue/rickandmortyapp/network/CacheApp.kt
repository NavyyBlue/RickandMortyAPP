package com.navyblue.rickandmortyapp.network

import com.navyblue.rickandmortyapp.domain.models.Character

object CacheApp {

    val characterMap = mutableMapOf<Int, Character>()
}