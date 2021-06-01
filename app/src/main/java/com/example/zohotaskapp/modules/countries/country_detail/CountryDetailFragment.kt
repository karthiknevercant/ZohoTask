package com.example.zohotaskapp.modules.countries.country_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zohotaskapp.Base.BaseFragment
import com.example.zohotaskapp.R
import com.example.zohotaskapp.databinding.CountryDetailFragmentBinding
import com.example.zohotaskapp.model.CountryItem
import com.example.zohotaskapp.utils.loadSvg
import com.google.android.material.chip.Chip

class CountryDetailFragment : BaseFragment() {

    private lateinit var countryDetailsMap: Map<String, String?>

    private var countryDetail: CountryItem? = null

    private lateinit var binding: CountryDetailFragmentBinding
//    private val viewModel by viewModel<CountriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountryDetailFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        countryDetail = getDataFromArgs()
        init(countryDetail)
    }

    private fun getDataFromArgs(): CountryItem? {
        var countryItem : CountryItem? = null
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

        countryDetailsMap = mapOf(
            getString(R.string.short_code) to countryDetail?.alpha3Code,
            getString(R.string.capital) to countryDetail?.capital,
            getString(R.string.region) to countryDetail?.region,
            getString(R.string.sub_region) to countryDetail?.subregion,
            getString(R.string.population) to countryDetail?.population.toString()
        )
        val adapter = CountryDetailAdapter(countryDetailsMap)
        binding.rvCountryDetails.adapter = adapter

        countryDetail?.languages?.forEach { lang ->
            val chip = Chip(binding.root.context)
            chip.text = lang.name
            binding.chipGroupCountryLangs.addView(chip)
        }
    }

    override fun onDestroy() {
        disableBackButtonOfActionBar()
        super.onDestroy()
    }
}