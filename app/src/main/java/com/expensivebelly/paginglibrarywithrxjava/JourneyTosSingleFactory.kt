package com.expensivebelly.paginglibrarywithrxjava

import android.util.Log
import com.expensivebelly.paginglibrarywithrxjava.tos.JourneyTO
import com.expensivebelly.paginglibrarywithrxjava.tos.toJourney
import com.fasterxml.jackson.databind.ObjectMapper
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.io.IOException

class JourneyTosSingleFactory(
    private val okHttpClient: OkHttpClient,
    private val mapper: ObjectMapper
) {

    fun create(
        departure: String,
        arrival: String,
        fromTime: String
    ): Single<List<JourneyTO>> = Single.create { emitter ->
        val response = okHttpClient.newCall(createRequest(departure, arrival, fromTime)).execute()
        if (!emitter.isDisposed) {
            try {
                if (response.isSuccessful) {
                    val htmlString: String? = response.body?.string()

                    val doc = Jsoup.parse(htmlString, "UTF-8")
                    var journeys = listOf<JourneyTO>()
                    for (i in 1..5) {
                        journeys = journeys + mapper.readValue(
                            doc.getElementById("jsonJourney-4-$i").dataNodes().toString().drop(
                                1
                            ).dropLast(1).trim().also { Log.d("Jackson", "$it") },
                            JourneyTO::class.java
                        ).also {
                            Log.d(
                                this.javaClass::getCanonicalName.toString(),
                                it.toJourney().toString()
                            )
                        }
                    }
                    if (!emitter.isDisposed) {
                        emitter.onSuccess(journeys)
                    }
                } else emitter.onError(Exception("Unsuccessful response with code ${response.code} and message ${response.message}"))
            } catch (exception: IOException) {
                if (!emitter.isDisposed) {
                    emitter.onError(exception)
                }
            }
        }
    }

    private fun createRequest(
        departureStation: String,
        arrivalStation: String,
        fromTime: String
    ): Request {
        return Request.Builder()
            .url("https://ojp.nationalrail.co.uk/service/timesandfares/$departureStation/$arrivalStation/today/$fromTime/dep")
            .build()
    }
}