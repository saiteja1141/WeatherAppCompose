package com.example.myapplication.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastItem(
	@SerializedName("dt") val dt: Long?,
	@SerializedName("main") val main: MainWeather?,
	@SerializedName("weather") val weather: List<Weather>?,
	@SerializedName("clouds") val clouds: Clouds?,
	@SerializedName("wind") val wind: Wind?,
	@SerializedName("visibility") val visibility: Int?,
	@SerializedName("pop") val pop: Double?,
	@SerializedName("rain") val rain: Rain?,
	@SerializedName("sys") val sys: Sys?,
	@SerializedName("dt_txt") val dtTxt: String?
) : Parcelable