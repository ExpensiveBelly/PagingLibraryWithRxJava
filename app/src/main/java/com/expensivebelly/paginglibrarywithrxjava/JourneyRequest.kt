package com.expensivebelly.paginglibrarywithrxjava

data class JourneyRequest(
    val departure: String,
    val arrival: String,
    val fromTime: String,
    val toTime: String = fromTime
)