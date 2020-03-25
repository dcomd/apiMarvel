package com.example.desafio_android_alexandre_nepomuceno.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataContainer(
    @Json(name = "offset")
    var offset: Int,
    @Json(name = "limit")
    var limit: Int,
    @Json(name = "total")
    var total: Int,
    @Json(name = "count")
    var count: Int,
    @Json(name = "results")
    var results: Array<Character>
)
