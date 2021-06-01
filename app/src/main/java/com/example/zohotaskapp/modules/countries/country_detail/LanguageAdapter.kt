package com.example.zohotaskapp.modules.countries.country_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zohotaskapp.databinding.LanguageItemLayoutBinding

class LanguageAdapter(val languages: List<String>) :
    RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding =
            LanguageItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return languages.size
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        languages[position].let {
                holder.binding.tvLang.text = it
        }
    }

    inner class LanguageViewHolder(val binding: LanguageItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}