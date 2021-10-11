package com.yash10019coder.nationsmvvmrxjavaapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {
    private val BASE_URL = "https://raw.githubusercontent.com"
    lateinit var api: CountriesApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }
    fun getCountries():Single<List<Country>> {
        return api.getCountries()
    }
}