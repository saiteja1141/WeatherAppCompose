package com.example.myapplication.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
	@SerializedName("id") val id: Int?,
	@SerializedName("main") val main: String?,
	@SerializedName("description") val description: String?,
	@SerializedName("icon") val icon: String?
) : Parcelable