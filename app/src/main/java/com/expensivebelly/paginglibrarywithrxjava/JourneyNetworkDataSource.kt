package com.expensivebelly.paginglibrarywithrxjava

import com.expensivebelly.paginglibrarywithrxjava.tos.toJourney
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class JourneyNetworkDataSource(
    private val journeyTosSingleFactory: JourneyTosSingleFactory,
    private val retryHandler: RetryHandler
) {

    fun fetch(departure: String, arrival: String, fromTime: String): Single<List<Journey>> =
        get(departure, arrival, fromTime)

    private fun get(
        departure: String,
        arrival: String,
        fromTime: String
    ) =
        journeyTosSingleFactory.create(
            departure,
            arrival,
            fromTime
        ).subscribeOn(Schedulers.io())
            .retryWhen { flowable ->
                retryHandler.handle(flowable)
            }
            .observeOn(Schedulers.computation())
            .map { journeys -> journeys.map { it.toJourney() } }
}