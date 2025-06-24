package com.example.myapplication.data.repository

import com.example.myapplication.data.model.WeatherResponse
import com.example.myapplication.data.network.WeatherService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val apiService: WeatherService
) {
    suspend fun getForecast(city: String): WeatherResponse {
        return apiService.getForecast(city)
    }
}
