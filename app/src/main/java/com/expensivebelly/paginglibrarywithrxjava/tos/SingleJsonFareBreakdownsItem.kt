package com.expensivebelly.paginglibrarywithrxjava.tos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SingleJsonFareBreakdownsItem(
    @JsonProperty("fareTicketType")
    val fareTicketType: String = "",
    @JsonProperty("fareRouteName")
    val fareRouteName: String = "",
    @JsonProperty("ticketPrice")
    val ticketPrice: Double = 0.0,
    @JsonProperty("nreFareCategory")
    val nreFareCategory: String = "",
    @JsonProperty("breakdownType")
    val breakdownType: String = "",
    @JsonProperty("tocName")
    val tocName: Any? = null,
    @JsonProperty("ticketRestriction")
    val ticketRestriction: Any? = null,
    @JsonProperty("discount")
    val discount: Int = 0,
    @JsonProperty("ticketType")
    val ticketType: String = "",
    @JsonProperty("cheapestFirstClassFare")
    val cheapestFirstClassFare: Double = 0.0,
    @JsonProperty("railcardName")
    val railcardName: String = "",
    @JsonProperty("fullFarePrice")
    val fullFarePrice: Double = 0.0,
    @JsonProperty("redRoute")
    val redRoute: Boolean = false,
    @JsonProperty("fareRouteDescription")
    val fareRouteDescription: String = "",
    @JsonProperty("passengerType")
    val passengerType: String = "",
    @JsonProperty("fareId")
    val fareId: Int = 0,
    @JsonProperty("numberOfTickets")
    val numberOfTickets: Int = 0,
    @JsonProperty("ticketTypeCode")
    val ticketTypeCode: String = "",
    @JsonProperty("tocProvider")
    val tocProvider: String = "",
    @JsonProperty("fareSetter")
    val fareSetter: String = "",
    @JsonProperty("fareProvider")
    val fareProvider: String = ""
)