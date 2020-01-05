package com.expensivebelly.paginglibrarywithrxjava

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

val <T> T.exhaustive: T
    get() = this

inline fun <reified T : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory) =
    ViewModelProviders.of(this, factory)[T::class.java]

inline fun <reified T : ViewModel> AppCompatActivity.viewModel(factory: ViewModelProvider.Factory) =
    ViewModelProviders.of(this, factory)[T::class.java]