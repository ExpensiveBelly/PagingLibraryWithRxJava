package com.expensivebelly.paginglibrarywithrxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    private val journeyAdapterPaged = JourneyAdapterPaged()

    private val viewModel: MainViewModel by lazy {
        viewModel<MainViewModel>(
            MainActivityViewModelFactory(
                JourneyRepository(
                    JourneyNetworkDataSource(
                        JourneyTosSingleFactory(
                            OkHttpClient(),
                            jacksonObjectMapper()
                        ), RetryHandler(this)
                    )
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.adapter = journeyAdapterPaged

        viewModel.state.observe(this, Observer {
            journeyAdapterPaged.submitList(it)
        })

        //Do this upon button click to change stations
//        viewModel.input.onNext(...)
    }
}
