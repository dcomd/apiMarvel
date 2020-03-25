package com.example.desafio_android_alexandre_nepomuceno.data.repository

import com.example.desafio_android_alexandre_nepomuceno.data.RetrofitInstanceMarvel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarvelRepository {

    suspend fun execute() = withContext(Dispatchers.IO) {
        val api = RetrofitInstanceMarvel.getRetrofit()
        return@withContext api.loadCharacters()
    }

    suspend fun executebyId(id: String) = withContext(Dispatchers.IO) {
        val api = RetrofitInstanceMarvel.getRetrofit()
        return@withContext api.loadCharacter(id)
    }
}