package com.yash10019coder.nationsmvvmrxjavaapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yash10019coder.nationsmvvmrxjavaapp.Country.Country

class ListViewModel : ViewModel() {
    var countryListData = MutableLiveData<List<Country>>()
    val loadingError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreshCountryList() {
        loadCountryList()
    }

    private fun loadCountryList() {
        val data = listOf<Country>(
            Country("Country1", 1),
            Country("Country2", 1),
            Country("Country3", 1),
            Country("Country4", 1),
            Country("Country5", 1),
            Country("Country6", 1),
            Country("Country7", 1),
            Country("Country8", 1),
            Country("Country9", 1),
            Country("Country10", 1),
        )
        loading.value = false
        loadingError.value = false
        countryListData.value = data
    }
}