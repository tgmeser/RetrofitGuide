package com.babyapps.retrofitguide.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RetroColor(
    @Json(name = "albumId")
    val albumId: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "thumbnailUrl")
    val thumbnailUrl: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String
)