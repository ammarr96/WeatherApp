package com.amar.weatherapp.api

import com.amar.weatherapp.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/forecast")
    suspend fun getWeatherForCity(@Query("q") cityName: String): ApiResponse

}