package com.example.zohotaskapp.modules.countries

import android.content.Context
import com.example.zohotaskapp.api.CountryApi
import com.example.zohotaskapp.model.CountryItem
import com.example.zohotaskapp.utils.AppResult
import com.example.zohotaskapp.utils.NetworkManager.isNetworkAvailable
import com.example.zohotaskapp.utils.Utils.handleApiError
import com.example.zohotaskapp.utils.Utils.handleSuccess
import com.example.zohotaskapp.utils.noNetworkConnectivityError

interface CountriesRepositary {
    suspend fun getCountries(): AppResult<List<CountryItem>>

    class CountriesRepositaryImpl(private val api: CountryApi, private val context: Context) :
        CountriesRepositary {
        override suspend fun getCountries(): AppResult<List<CountryItem>> {
            return if (isNetworkAvailable(context)) {
                return try {
                    val response = api.getCountries()
                    if (response.isSuccessful) {
                        //save the data
                        response.body()?.let {

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
                context.noNetworkConnectivityError()
            }
        }
    }
}