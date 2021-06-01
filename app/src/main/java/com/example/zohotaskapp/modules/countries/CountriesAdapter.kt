package com.example.zohotaskapp.modules.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.zohotaskapp.databinding.CountryItemLayoutBinding
import com.example.zohotaskapp.model.CountryItem
import com.example.zohotaskapp.utils.loadSvg

class CountriesAdapter(
    val countries: List<CountryItem>,
    val itemClick: (countryDetail: CountryItem) -> Unit
) :
    RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val binding =
            CountryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {

        holder.apply {
            countries[position].apply {
                val countryDetail = this

                binding.apply {
                    flag?.let {
                        val set = ConstraintSet()
                        val ratio: String
                        if (position % 2 == 0) {
                            ratio = String.format("%d:%d", 260, 260)
                        } else {
                            ratio = String.format("%d:%d", 450, 260)
                        }
                        set.clone(clItem)
                        set.setDimensionRatio(imgvCountry.id, ratio)
                        set.applyTo(clItem)
                        imgvCountry.loadSvg(it)
                    }

                    name.let {
                        tvCountry.text = it
                    }

                    root.setOnClickListener {
                        itemClick(countryDetail)
                    }
                }
            }
        }
    }

    inner class CountriesViewHolder(val binding: CountryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}