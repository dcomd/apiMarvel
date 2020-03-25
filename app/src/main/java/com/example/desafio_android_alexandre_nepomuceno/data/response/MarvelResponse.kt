package com.example.desafio_android_alexandre_nepomuceno.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MarvelResponse(
    @Json(name = "data")
    var data: CharacterDataContainer

)