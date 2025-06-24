package com.example.myapplication.data.network

import com.example.myapplication.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "65d00499677e59496ca2f318eb68c049"
    ) : WeatherResponse
}