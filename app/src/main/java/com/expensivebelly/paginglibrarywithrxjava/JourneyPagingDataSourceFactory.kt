package com.expensivebelly.paginglibrarywithrxjava

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable

class JourneyPagingDataSourceFactory(
    private val journeyRepository: JourneyRepository,
    private val disposables: CompositeDisposable
) : DataSource.Factory<String, JourneyViewState>() {

    lateinit var journeyDataSource: JourneyDataSource

    override fun create() =
        JourneyDataSource(journeyRepository, disposables).also { journeyDataSource = it }
}