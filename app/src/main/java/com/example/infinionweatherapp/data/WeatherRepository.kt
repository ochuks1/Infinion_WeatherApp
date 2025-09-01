package com.example.infinionweatherapp.data

import com.example.infinionweatherapp.model.WeatherResponse

class WeatherRepository(private val api: WeatherApi) {

    suspend fun getWeather(city: String, apiKey: String): WeatherResponse {
        return api.getWeather(city, apiKey)
    }
}