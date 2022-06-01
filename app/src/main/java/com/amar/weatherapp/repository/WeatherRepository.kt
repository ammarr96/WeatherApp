package com.amar.weatherapp.repository

import android.content.Context
import com.amar.weatherapp.api.ApiService
import com.amar.weatherapp.model.ApiResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.IOException
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val context: Context,
    private val apiService: ApiService
) {

    suspend fun getWeatherData(isLive: Boolean) : ApiResponse {
        if (isLive) {
            return apiService.getWeatherForCity("Sarajevo")
        }
        else {
            return getLocalWeatherData()
        }
    }

    private fun getLocalWeatherData(): ApiResponse {
        val jsonFileString = getJsonDataFromAsset(context, "response.json")

        val gson = Gson()
        val response: ApiResponse= gson.fromJson(jsonFileString, ApiResponse::class.java)

        return response
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}