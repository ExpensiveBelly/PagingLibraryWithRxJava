package com.expensivebelly.paginglibrarywithrxjava

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject

class MainViewModel(
    journeyRepository: JourneyRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val journeyPagingDataSourceFactory =
        JourneyPagingDataSourceFactory(journeyRepository, compositeDisposable)

    val state: LiveData<PagedList<JourneyViewState>>
        get() = LivePagedListBuilder(
            journeyPagingDataSourceFactory,
            PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(5).build()
        ).build()

    val input: PublishSubject<Unit> = PublishSubject.create()

    init {
        journeyRepository.journey.onNext(
            JourneyRequest(
                departure = "KGX",
                arrival = "LST",
                fromTime = nowPlus5MinutesFormatted
            )
        )

        //TODO: Change journey upon user click / edit fields
        compositeDisposable += input.subscribe {
            journeyRepository.journey.onNext(
                JourneyRequest(
                    departure = "KGX",
                    arrival = "LST",
                    fromTime = nowPlus5MinutesFormatted
                )
            )
        }

    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}