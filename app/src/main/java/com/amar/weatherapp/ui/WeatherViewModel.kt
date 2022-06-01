package com.amar.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.weatherapp.model.WeatherInfo
import com.amar.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private var weatherRepository: WeatherRepository
) : ViewModel() {

    private val _isLive = MutableStateFlow(true)

    private val _weatherList = MutableStateFlow(listOf<WeatherInfo>())

    private val _weatherHashMap = MutableStateFlow(mapOf<String, List<WeatherInfo>>())
    val weatherHashMap = _weatherHashMap.asStateFlow()

    fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherList.value = weatherRepository.getWeatherData(_isLive.value).list;

            val mapOfList = _weatherList.value
                .groupBy { it.dt_txt.substring(0, 10) }

            mapOfList.keys.forEach {
                System.out.println(it)
            }
            _weatherHashMap.value = mapOfList
        }
    }

    fun setIsLive(isLive: Boolean) {
        _isLive.value = isLive
        getWeatherData()
    }

}