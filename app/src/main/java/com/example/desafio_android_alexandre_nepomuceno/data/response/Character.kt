package com.example.desafio_android_alexandre_nepomuceno.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    @Json(name = "id")
    var id: Long,
    @Json(name = "name")
    var name: String,
    @Json(name = "description")
    var description: String,
    @Json(name = "thumbnail")
    var thumbnail: Image?

)




