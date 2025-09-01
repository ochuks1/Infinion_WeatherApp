package com.example.infinionweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infinionweatherapp.data.WeatherRepository
import com.example.infinionweatherapp.model.WeatherResponse
import com.example.infinionweatherapp.data.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository = WeatherRepository(RetrofitInstance.api)) : ViewModel() {

    private val _weather = MutableLiveData<WeatherResponse?>()
    val weather: LiveData<WeatherResponse?> = _weather

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getWeather(city, apiKey)
                _weather.postValue(response)
            } catch (e: Exception) {
                _weather.postValue(null)
            }
        }
    }
}
