package com.expensivebelly.paginglibrarywithrxjava

import io.reactivex.subjects.BehaviorSubject


class JourneyRepository(
    private val journeyNetworkDataSource: JourneyNetworkDataSource
) {

    var journey = BehaviorSubject.create<JourneyRequest>()

    fun fetch(request: JourneyRequest) =
        journeyNetworkDataSource.fetch(
            request.departure,
            request.arrival,
            request.fromTime
        )
}
