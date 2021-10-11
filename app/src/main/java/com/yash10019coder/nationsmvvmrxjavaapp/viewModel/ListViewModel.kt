package com.yash10019coder.nationsmvvmrxjavaapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yash10019coder.nationsmvvmrxjavaapp.model.CountriesService
import com.yash10019coder.nationsmvvmrxjavaapp.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {
    private val countryService = CountriesService()
    private val disposable = CompositeDisposable()
    var countryListData = MutableLiveData<List<Country>>()
    val loadingError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreshCountryList() {
        loadCountryList()
    }

    private fun loadCountryList() {
        loading.value = true
        disposable.add(
            countryService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(value: List<Country>?) {
                        countryListData.value = value
                        loadingError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        loadingError.value = true
                        loading.value = false
                    }

                }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}