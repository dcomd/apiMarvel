package com.example.desafio_android_alexandre_nepomuceno.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image (
    @Json(name = "path")
    var path: String,
    @Json(name = "extension")
    var extension: String
)