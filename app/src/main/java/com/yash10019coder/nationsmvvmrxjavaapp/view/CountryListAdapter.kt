package com.yash10019coder.nationsmvvmrxjavaapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yash10019coder.nationsmvvmrxjavaapp.model.Country
import com.yash10019coder.nationsmvvmrxjavaapp.R
import com.yash10019coder.nationsmvvmrxjavaapp.util.getProgressDrawable
import com.yash10019coder.nationsmvvmrxjavaapp.util.loadImage

class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountires: List<Country>) {
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
        val capital = view.findViewById<TextView>(R.id.capital)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val progressDrawable = getProgressDrawable(view.context)
        fun bind(country: Country) {
            countryName.text = country.countryName
            capital.text = country.capital
            imageView.loadImage(country.flagPNG, progressDrawable)
        }
    }
}