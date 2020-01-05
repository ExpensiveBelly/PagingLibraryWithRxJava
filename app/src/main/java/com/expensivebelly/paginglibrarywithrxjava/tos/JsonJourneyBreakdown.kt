package com.expensivebelly.paginglibrarywithrxjava.tos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class JsonJourneyBreakdown(
    @JsonProperty("departureTime")
    val departureTime: String = "",
    @JsonProperty("durationHours")
    val durationHours: Int = 0,
    @JsonProperty("arrivalStationCRS")
    val arrivalStationCRS: String = "",
    @JsonProperty("changes")
    val changes: Int = 0,
    @JsonProperty("statusIcon")
    val statusIcon: String = "",
    @JsonProperty("journeyId")
    val journeyId: Int = 0,
    @JsonProperty("statusMessage")
    val statusMessage: String?,
    @JsonProperty("durationMinutes")
    val durationMinutes: Int = 0,
    @JsonProperty("departureStationCRS")
    val departureStationCRS: String = "",
    @JsonProperty("arrivalTime")
    val arrivalTime: String = "",
    @JsonProperty("departureStationName")
    val departureStationName: String = "",
    @JsonProperty("arrivalStationName")
    val arrivalStationName: String = "",
    @JsonProperty("hoverInformation")
    val hoverInformation: Any? = null,
    @JsonProperty("responseId")
    val responseId: Int = 0
)