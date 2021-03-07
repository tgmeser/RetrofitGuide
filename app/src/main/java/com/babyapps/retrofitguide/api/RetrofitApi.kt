package com.babyapps.retrofitguide.api

import com.babyapps.retrofitguide.model.RetroColor
import retrofit2.Response
import retrofit2.http.GET


interface RetrofitApi  {
    @GET("/photos")
    suspend fun get(): Response<List<RetroColor>>
}