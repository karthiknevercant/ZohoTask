package com.example.zohotaskapp.api

import com.example.zohotaskapp.model.CountryItem
import com.example.zohotaskapp.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApi {
    @GET("all")
    suspend fun getCountries(): List<CountryItem>

    @GET("https://api.openweathermap.org/data/2.5/weather")
    suspend fun getWeather(@Query("q") cityName : String, @Query("appid") apiKey : String): WeatherData
}