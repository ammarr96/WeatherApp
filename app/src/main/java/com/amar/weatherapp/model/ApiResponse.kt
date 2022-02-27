package com.amar.weatherapp.model

data class ApiResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherInfo>,
    val message: Int
)