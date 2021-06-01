package com.example.zohotaskapp.modules.countries

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.zohotaskapp.Base.BaseActivity
import com.example.zohotaskapp.Base.BaseFragment
import com.example.zohotaskapp.R
import com.example.zohotaskapp.databinding.CountriesFragmentBinding
import com.example.zohotaskapp.model.CountryItem
import com.example.zohotaskapp.utils.showMessage
import org.koin.android.viewmodel.ext.android.viewModel

class CountriesFragment : BaseFragment() {
    private lateinit var binding: CountriesFragmentBinding
    private val viewModel by viewModel<CountriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountriesFragmentBinding.inflate(inflater)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()

        if (viewModel.countryList.value == null)
            viewModel.getCountries()

        // Obervers - Update Data to UI
        viewModel.filteredCountryList.observe(viewLifecycleOwner, Observer {
            Log.d("@Counties", it.toString())

            val adapter = CountriesAdapter(it, ::goToDetail)
            binding.rvCountries.adapter = adapter
        })

        viewModel.showError.observe(viewLifecycleOwner, Observer {
            it?.let { (activity as AppCompatActivity).showMessage(it) }
        })

        viewModel.showLoading.observe(viewLifecycleOwner, Observer {
            if (it)
                (activity as? BaseActivity)?.showProgressbar()
            else
                (activity as? BaseActivity)?.hideProgressbar()
        })

        // Clicks
        binding.sv.setOnClickListener {
            binding.sv.onActionViewExpanded()
        }

        binding.sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //Performs search when user hit the search button on the keyboard if you need
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                //Start filtering the list as user start entering the characters
                viewModel.countryList.value?.let {
                    viewModel.filterCountries(p0, it)
                }
                return false
            }
        })
    }

    fun init() {
        updateActionBarTitle(getString(R.string.countries))
    }

    fun goToDetail(countryDetail: CountryItem) {
        val action = CountriesFragmentDirections.actionCountriesFragment2ToCountryDetailFragment(
            countryDetail
        )
        findNavController().navigate(action)
    }
}