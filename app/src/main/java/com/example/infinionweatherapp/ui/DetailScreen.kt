package com.example.infinionweatherapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.infinionweatherapp.R
import com.example.infinionweatherapp.viewmodel.WeatherViewModel

class DetailsActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private val apiKey = "6a05a774e53fef913775230f572622fc"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val city = intent.getStringExtra("city") ?: ""
        val tempText = findViewById<TextView>(R.id.tempText)
        val descText = findViewById<TextView>(R.id.descText)

        viewModel.weather.observe(this) { weather ->
            if (weather != null) {
                tempText.text = "Temperature: ${weather.main.temp}°C"
                descText.text = "Condition: ${weather.weather[0].description}"
            } else {
                tempText.text = "Error loading data"
                descText.text = ""
            }
        }

        viewModel.fetchWeather(city, apiKey)
    }
}