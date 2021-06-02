package com.example.zohotaskapp.modules.countries.country_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zohotaskapp.Base.BaseFragment
import com.example.zohotaskapp.R
import com.example.zohotaskapp.databinding.CountryDetailFragmentBinding
import com.example.zohotaskapp.model.CountryItem
import com.example.zohotaskapp.modules.countries.CountriesViewModel
import com.example.zohotaskapp.utils.loadSvg
import com.google.android.material.chip.Chip
import org.koin.android.viewmodel.ext.android.viewModel

class CountryDetailFragment : BaseFragment() {

    val celciusSymbol = "°"

    private var countryDetailsMap = mutableMapOf<String, String>()
    private var countryDetail: CountryItem? = null
    private val viewModel by viewModel<CountriesViewModel>()
    private lateinit var binding: CountryDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountryDetailFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryDetail = getDataFromArgs()
        init(countryDetail)

        if (viewModel.weather.value == null) {
            countryDetail?.capital?.let {
                viewModel.getWeather(it)
            }
        }

        // Obervers - Update Data to UI
//        viewModel.weather.observe(viewLifecycleOwner, Observer {
//            Log.d("@weather", it.toString())

//            Log.d("@weather", it.toString()

//            it.main?.temp?.let {
//                binding.tvWeather.text = it.roundToInt().toString() + celciusSymbol
//            }
//
//            binding.tvWeatherTitle.visibility = View.VISIBLE
//
//            it.weather?.get(0)?.main.let {
//                binding.tvWeatherText.text = it
//            }
//        })
    }

    private fun getDataFromArgs(): CountryItem? {
        var countryItem: CountryItem? = null
        arguments?.let {
            countryItem = CountryDetailFragmentArgs.fromBundle(it).countryDetail
        }
        return countryItem
    }

    private fun init(countryDetail: CountryItem?) {
        // Update Action Bar
        enableBackButtonOfActionBar()
        updateActionBarTitle(getString(R.string.country_detail))
        updateCountryDetail(countryDetail)
    }

    private fun updateCountryDetail(countryDetail: CountryItem?) {
        // Update Data to UI
        binding.tvCountryName.text = countryDetail?.name
        countryDetail?.flag?.let {
            binding.imgvCountry.loadSvg(it)
        }

        addToMap(getString(R.string.capital), countryDetail?.capital, countryDetailsMap)
        addToMap(getString(R.string.short_code), countryDetail?.alpha3Code, countryDetailsMap)
        addToMap(getString(R.string.region), countryDetail?.region, countryDetailsMap)
        addToMap(getString(R.string.sub_region), countryDetail?.subregion, countryDetailsMap)

        val adapter = CountryDetailAdapter(countryDetailsMap)
        binding.rvCountryDetails.adapter = adapter

        countryDetail?.languages?.forEach { lang ->
            val chip = Chip(binding.root.context)
            chip.isClickable = false
            chip.text = lang.name
            binding.chipGroupCountryLangs.addView(chip)
        }
    }

    override fun onDestroy() {
        disableBackButtonOfActionBar()
        super.onDestroy()
    }

    private fun addToMap(key: String, value: String?, map: MutableMap<String, String>) {
        value?.let {
            map.put(key, it)
        }
    }
}