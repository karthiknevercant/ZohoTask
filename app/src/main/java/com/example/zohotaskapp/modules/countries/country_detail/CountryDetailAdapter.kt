package com.example.zohotaskapp.modules.countries.country_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zohotaskapp.databinding.CountryDetailItemLayoutBinding

class CountryDetailAdapter(val countryDetailsMap: Map<String, String?>) :
    RecyclerView.Adapter<CountryDetailAdapter.CountryDetailsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryDetailsViewHolder {
        val binding = CountryDetailItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CountryDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryDetailsMap.size
    }

    override fun onBindViewHolder(holder: CountryDetailsViewHolder, position: Int) {
        holder.binding.apply {
            val title = countryDetailsMap.keys.elementAt(position)
            tvTitle.text = title
            tvValue.text = countryDetailsMap[title]
        }
    }

    inner class CountryDetailsViewHolder(val binding: CountryDetailItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}