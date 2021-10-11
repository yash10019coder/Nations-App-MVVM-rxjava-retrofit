package com.yash10019coder.nationsmvvmrxjavaapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yash10019coder.nationsmvvmrxjavaapp.Country.Country
import com.yash10019coder.nationsmvvmrxjavaapp.R

class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountires:List<Country>) {
        countries.clear()
        countries.addAll(newCountires)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int = countries.size

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryName = view.findViewById<TextView>(R.id.name)
        fun bind(country: Country) {
            countryName.text= country.countryName
        }
    }
}