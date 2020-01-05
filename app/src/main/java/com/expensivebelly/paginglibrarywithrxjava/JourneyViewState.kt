package com.expensivebelly.paginglibrarywithrxjava

import android.annotation.SuppressLint

class JourneyViewState(
    val id: JourneyId,
    val departureTime: String,
    statusMessage: String,
    val arrivalTime: String,
    val departureStationName: String,
    val arrivalStationName: String
) {
    @SuppressLint("DefaultLocale")
    val statusMessage = statusMessage.capitalize()
    val statusMessageTextColor = when {
        statusMessage.contains("cancelled", true) -> TextColor.RED
        statusMessage.contains("late", true) || statusMessage.contains(
            "delay",
            true
        ) -> TextColor.ORANGE
        else -> TextColor.GREEN
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JourneyViewState

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

enum class TextColor {
    RED,
    ORANGE,
    GREEN
}

