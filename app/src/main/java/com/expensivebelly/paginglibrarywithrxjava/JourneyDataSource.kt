package com.expensivebelly.paginglibrarywithrxjava

import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

class JourneyDataSource(
    private val journeyRepository: JourneyRepository,
    private val disposables: CompositeDisposable
) : PageKeyedDataSource<String, JourneyViewState>() {

    init {
        disposables += journeyRepository.journey
            .skip(1)
            .doOnNext { invalidate() } //This will start fetching the new departure/arrival stations
            .subscribe()
    }

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, JourneyViewState>
    ) {
        disposables += journeyRepository.journey
            .distinctUntilChanged()
            .switchMapSingle {
                journeyRepository.fetch(it)
            }
            .subscribeBy { journeys ->
                callback.onResult(
                    journeys.map { it.toJourneyViewState() },
                    null,
                    adjacentPageKey(journeys)
                )
            }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, JourneyViewState>
    ) {
        disposables += journeyRepository.journey
            .distinctUntilChanged()
            .switchMapSingle {
                journeyRepository.fetch(it.copy(fromTime = params.key))
            }
            .subscribeBy { journeys ->
                callback.onResult(
                    journeys.map { it.toJourneyViewState() },
                    adjacentPageKey(journeys)
                )
            }
    }

    private fun adjacentPageKey(journeys: List<Journey>): String? {
        val dateInString = journeys.lastOrNull()?.departureTime ?: return ""
        val localTime = LocalTime.parse(dateInString, DateTimeFormatter.ofPattern("HH:mm"))
        return localTime.plusMinutes(10).format(DateTimeFormatter.ofPattern("HHmm"))
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, JourneyViewState>
    ) {
    }
}