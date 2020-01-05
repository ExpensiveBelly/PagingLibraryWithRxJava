package com.expensivebelly.paginglibrarywithrxjava

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MainActivityViewModelFactory(
    private val journeyRepository: JourneyRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(journeyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}