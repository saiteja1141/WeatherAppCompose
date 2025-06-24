package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
	@SerializedName("cod") val cod: String?,
	@SerializedName("message") val message: Int?,
	@SerializedName("cnt") val cnt: Int?,
	@SerializedName("list") val list: List<ForecastItem>?,
	@SerializedName("city") val city: City?
)