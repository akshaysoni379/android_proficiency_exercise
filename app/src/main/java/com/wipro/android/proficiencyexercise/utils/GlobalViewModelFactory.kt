@file:Suppress("UNCHECKED_CAST")

package com.wipro.android.proficiencyexercise.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import javax.inject.Inject

class GlobalViewModelFactory<T> @Inject
constructor(private val viewModel: T) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}