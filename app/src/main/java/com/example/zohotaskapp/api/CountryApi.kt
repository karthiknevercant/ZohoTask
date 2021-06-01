package com.example.zohotaskapp.api

import com.example.zohotaskapp.model.CountryItem
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("all")
    suspend fun getCountries(): Response<List<CountryItem>>
}