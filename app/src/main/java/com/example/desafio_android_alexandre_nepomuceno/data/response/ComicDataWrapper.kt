package com.example.desafio_android_alexandre_nepomuceno.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicDataWrapper(
    @Json(name = "data")
    val data : ComicDataContainer
)

