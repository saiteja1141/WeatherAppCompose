package com.example.myapplication.util

fun kelvinToFahrenheit(kelvin: Double): Int {
    return ((kelvin - 273.15) * 9 / 5 + 32).toInt()
}

fun kelvinToCelsius(kelvin: Double): Int {
    return (kelvin - 273.15).toInt()
}
