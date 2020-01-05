package com.expensivebelly.paginglibrarywithrxjava


data class Journey(
    val id: Int,
    val departureTime: String,
    val statusMessage: String?,
    val arrivalTime: String,
    val departureStationName: String,
    val arrivalStationName: String,
    val statusIcon: String = "",
    val departureStationCRS: String = "",
    val arrivalStationCRS: String = "",
    val changes: Int = 0
)

fun Journey.toJourneyViewState(): JourneyViewState {
    return JourneyViewState(
        id = JourneyId(id),
        departureTime = departureTime,
        statusMessage = statusMessage ?: "-",
        arrivalTime = arrivalTime,
        departureStationName = departureStationName,
        arrivalStationName = arrivalStationName
    )
}