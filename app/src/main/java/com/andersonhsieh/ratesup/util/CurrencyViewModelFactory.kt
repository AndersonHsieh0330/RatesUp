package com.andersonhsieh.ratesup.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andersonhsieh.ratesup.data.Repository
import com.andersonhsieh.ratesup.ui.home.HomeViewModel

class CurrencyViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository)
                else -> {
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T
        }
    }
    //custom viewmodelfactory for passing in argument into view model


}