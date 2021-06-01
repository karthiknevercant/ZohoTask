package com.example.zohotaskapp.modules.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zohotaskapp.model.CountryItem
import com.example.zohotaskapp.utils.AppResult
import com.example.zohotaskapp.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CountriesViewModel(private val repository: CountriesRepositary) : ViewModel() {

    init {
//        this.getCountries()
    }

    var showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String?>()
    val countryList = MutableLiveData<List<CountryItem>>()
    var filteredCountryList = MutableLiveData<List<CountryItem>>()

    fun getCountries() {
        showLoading.value = true
        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
            val result = repository.getCountries()
//            }
            showLoading.value = false
            when (result) {
                is AppResult.Success -> {
                    result.successData.let {
                        countryList.value = it
                        filteredCountryList.value = it

                    }
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }

    fun filterCountries(searchTxt: String?, countries: List<CountryItem>) {
//        viewModelScope.launch(Dispatchers.Default) {
        if (searchTxt.isNullOrBlank()) {
            filteredCountryList.value = countryList.value
        } else {
            val list = countries.filter { it -> it.name?.contains(searchTxt, true) ?: false }
            filteredCountryList.value = list
        }
//        }
    }

    fun saveToDb(countries: List<CountryItem>) {

    }
}