package com.example.infinionweatherapp.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.infinionweatherapp.R

class HomeActivity : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var cityInput: EditText
    private lateinit var searchBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPrefs = getSharedPreferences("weather_prefs", MODE_PRIVATE)

        cityInput = findViewById(R.id.cityInput)
        searchBtn = findViewById(R.id.searchBtn)

        // Load favourite city
        val savedCity = sharedPrefs.getString("fav_city", "")
        cityInput.setText(savedCity)

        searchBtn.setOnClickListener {
            val city = cityInput.text.toString()
            sharedPrefs.edit().putString("fav_city", city).apply()

            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("city", city)
            startActivity(intent)
        }
    }
}