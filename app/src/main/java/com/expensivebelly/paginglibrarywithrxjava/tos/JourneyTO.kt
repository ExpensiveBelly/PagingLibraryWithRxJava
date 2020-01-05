package com.expensivebelly.paginglibrarywithrxjava.tos

import com.expensivebelly.paginglibrarywithrxjava.Journey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class JourneyTO(
    @JsonProperty("jsonJourneyBreakdown")
    val jsonJourneyBreakdown: JsonJourneyBreakdown,
    @JsonProperty("singleJsonFareBreakdowns")
    val singleJsonFareBreakdowns: List<SingleJsonFareBreakdownsItem>?
)

fun JourneyTO.toJourney() = Journey(
    id = -1,
    departureTime = jsonJourneyBreakdown.departureTime,
    statusMessage = jsonJourneyBreakdown.statusMessage ?: "",
    arrivalTime = jsonJourneyBreakdown.arrivalTime,
    departureStationName = jsonJourneyBreakdown.departureStationName,
    arrivalStationName = jsonJourneyBreakdown.arrivalStationName,
    statusIcon = jsonJourneyBreakdown.statusIcon,
    departureStationCRS = jsonJourneyBreakdown.departureStationCRS,
    arrivalStationCRS = jsonJourneyBreakdown.arrivalStationCRS,
    changes = jsonJourneyBreakdown.changes
)