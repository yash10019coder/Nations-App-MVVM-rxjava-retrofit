package com.yash10019coder.nationsmvvmrxjavaapp.view

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yash10019coder.nationsmvvmrxjavaapp.R
import com.yash10019coder.nationsmvvmrxjavaapp.viewModel.ListViewModel


class MainActivity : AppCompatActivity() {

    lateinit var list_error: TextView
    lateinit var viewModel: ListViewModel
    lateinit var progress_view: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var swiperefreshlayout: SwipeRefreshLayout
    private val countryListAdapter = CountryListAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refreshCountryList()
        swiperefreshlayout = findViewById(R.id.swiperefreshlayout)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryListAdapter
        }
        observeViewModel(viewModel)
        swiperefreshlayout.setOnRefreshListener {
            Handler().postDelayed(Runnable {
                swiperefreshlayout.isRefreshing = false
            }, 0)
            viewModel.refreshCountryList()
        }
    }

    private fun observeViewModel(viewModel: ListViewModel) {
        list_error = findViewById<TextView>(R.id.textView2)
        progress_view = findViewById<ProgressBar>(R.id.progressBar)
        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_view.visibility = if (it) View.VISIBLE else View.INVISIBLE
                if (it)
                    recyclerView.visibility = View.INVISIBLE
                list_error.visibility = View.INVISIBLE
            }
        })
        viewModel.countryListData.observe(this, Observer { countries ->
            countries?.let {
                recyclerView.visibility = View.VISIBLE
                countryListAdapter.updateCountries(it)
            }
        })
        viewModel.loadingError.observe(this, Observer { isError: Boolean? ->
            isError?.let {
                list_error.visibility =
                    if (it)
                        View.VISIBLE
                    else
                        View.INVISIBLE
            }
        })
    }
}