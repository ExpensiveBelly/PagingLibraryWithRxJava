package com.expensivebelly.paginglibrarywithrxjava

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.journey_row_base.view.*


class JourneyAdapterPaged :
    PagedListAdapter<JourneyViewState, JourneyViewHolder>(EqualsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JourneyViewHolder =
        JourneyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_journey,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: JourneyViewHolder, position: Int) {
        holder.bind(getItem(position) as JourneyViewState)
    }
}

class JourneyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(journey: JourneyViewState) {
        itemView.departure_station_view.text = journey.departureStationName
        itemView.arrival_station_view.text = journey.arrivalStationName
        itemView.departure_time_view.text = journey.departureTime
        itemView.arrival_time_view.text = journey.arrivalTime
        itemView.status_message_view.text = journey.statusMessage
        itemView.status_message_view.setTextColor(
            ContextCompat.getColor(
                itemView.context,
                when (journey.statusMessageTextColor) {
                    TextColor.RED -> android.R.color.holo_red_dark
                    TextColor.ORANGE -> android.R.color.holo_orange_dark
                    TextColor.GREEN -> android.R.color.holo_green_dark
                }
            )
        )
    }
}



