package com.example.zohotaskapp.modules.countries

import android.content.Context
import android.util.Log
import com.example.zohotaskapp.api.CountryApi
import com.example.zohotaskapp.database.CountryDao
import com.example.zohotaskapp.model.CountryItem
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

    class CountriesRepositaryImpl(
        private val api: CountryApi,
        private val context: Context,
        private val dao: CountryDao
    ) :
        CountriesRepositary {
        override suspend fun getCountries(): AppResult<List<CountryItem>> {
            return if (isNetworkAvailable(context)) {
                return try {
                    val response = api.getCountries()
                    if (response.isSuccessful) {
                        //save the data
                        response.body()?.let {
                            withContext(Dispatchers.IO) { dao.addAll(it) }
                        }
                        handleSuccess(response)
                    } else {
                        handleApiError(response)
                    }
                } catch (e: Exception) {
                    AppResult.Error(e)
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

        private suspend fun getCountriesDataFromDb(): List<CountryItem> {
            return withContext(Dispatchers.IO) {
                dao.getCountries()
            }
        }
    }
}