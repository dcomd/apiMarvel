package com.example.desafio_android_alexandre_nepomuceno.data

import com.example.desafio_android_alexandre_nepomuceno.data.interfaces.Marvel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstanceMarvel {

    fun getRetrofit(): Marvel {
        val BASE_URL = "http://gateway.marvel.com/"
        val mosh = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(mosh))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(Marvel::class.java)
    }
}