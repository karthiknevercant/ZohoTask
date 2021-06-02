package com.example.zohotaskapp.modules.countries

import android.content.Context
import android.util.Log
import com.example.zohotaskapp.AppConstants
import com.example.zohotaskapp.R
import com.example.zohotaskapp.api.CountryApi
import com.example.zohotaskapp.database.CountryDao
import com.example.zohotaskapp.model.CountryItem
import com.example.zohotaskapp.model.WeatherData
import com.example.zohotaskapp.utils.AppResult
import com.example.zohotaskapp.utils.NetworkManager.isNetworkAvailable
import com.example.zohotaskapp.utils.TAG
import com.example.zohotaskapp.utils.Utils.handleApiError
import com.example.zohotaskapp.utils.Utils.handleSuccess
import com.example.zohotaskapp.utils.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CountriesRepositary {
    suspend fun getCountries(): AppResult<List<CountryItem>>
    suspend fun getWeather(city: String): AppResult<WeatherData>

    class CountriesRepositaryImpl(
        private val api: CountryApi,
        private val context: Context,
        private val dao: CountryDao
    ) :
        CountriesRepositary {
        override suspend fun getCountries(): AppResult<List<CountryItem>> {
            return if (isNetworkAvailable(context)) {
                val response = api.getCountries()
                if (!response.isNullOrEmpty()) {
                    //save the data
                    withContext(Dispatchers.IO) { dao.addAll(response) }
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } else {
                //no network
                val countryList = getCountriesDataFromDb()
                return if (countryList.isNotEmpty()) {
                    Log.d(TAG, "from db")
                    AppResult.Success(countryList)
                } else
                //no network
                    context.noNetworkConnectivityError()
            }
        }

        override suspend fun getWeather(city: String): AppResult<WeatherData> {
            return if (isNetworkAvailable(context)) {
                val response = api.getWeather(city, AppConstants.WEATHER_API_KEY)
                Log.d("weatherresponse", response.toString())
                handleSuccess(response)
            } else {
                //no network
                return AppResult.Error(Exception(context.getString(R.string.no_network_connectivity)))
            }
        }

        private suspend fun getCountriesDataFromDb(): List<CountryItem> {
            return withContext(Dispatchers.IO) {
                dao.getCountries()
            }
        }
    }
}