package com.babyapps.retrofitguide.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val BASE_URL = "https://jsonplaceholder.typicode.com"
    val api: RetrofitApi by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitApi::class.java)
    }
}