package com.andersonhsieh.ratesup.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.andersonhsieh.ratesup.data.Repository
import com.andersonhsieh.ratesup.model.APIResponseObject
import com.andersonhsieh.ratesup.model.ExtractedCurrencyHistoryData
import com.andersonhsieh.ratesup.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
//this @HiltViewModel annotation is for fragment and activity to be able to get the ViewModel
class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val currencyData = MutableLiveData<ExtractedCurrencyHistoryData>()

    private val inputCurrency = MutableLiveData<String>()

    private val outputCurrency = MutableLiveData<String>()

    fun getFromCurrencyStringLiveData(): LiveData<String> {
        return inputCurrency
    }

    fun updateFromCurrencyString(input: String) {
        //give the livedata a value and it'll assign it in main thread
        inputCurrency.postValue(input)
    }

    fun getToCurrencyStringLiveData(): LiveData<String> {
        return outputCurrency
    }

    fun updateToCurrencyString(output: String) {
        outputCurrency.postValue(output)
    }


    fun getLiveData(): LiveData<ExtractedCurrencyHistoryData> {
        //return livedata instead of mutablelivedata
        //to avoid changes to livedata.value outside of viewmodel by accident
        return currencyData
    }

    fun fetchDataFromAPI(
        fromCurrency: String,
        toCurrency: String,
        toDate: String,
        oneMonthAgoTimeStamp: String,
        twoMonthsAgoTimStamp: String, fromDate: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            updateFromCurrencyString(fromCurrency)
            updateToCurrencyString(toCurrency)
            val response = repository.getData(fromCurrency, fromDate, toDate)

            try {
                val currentValue = response.data.get(toDate)?.get(toCurrency) ?: 0.0
                val oneMonthAgoValue =
                    response.data[oneMonthAgoTimeStamp]?.get(toCurrency) ?: 0.0;
                val twoMonthsAgoValue =
                    response.data[twoMonthsAgoTimStamp]?.get(toCurrency) ?: 0.0;
                val threeMonthsAgoValue =
                    response.data[fromDate]?.get(toCurrency) ?: 0.0;


                currencyData.postValue(
                    ExtractedCurrencyHistoryData(
                        toDate,
                        currentValue,
                        oneMonthAgoTimeStamp,
                        oneMonthAgoValue,
                        twoMonthsAgoTimStamp,
                        twoMonthsAgoValue,
                        fromDate,
                        threeMonthsAgoValue,
                        fromCurrency,
                        toCurrency
                    )
                )

            } catch (e: Exception) {
                Log.d(Constants.loggingTag, "API request failed")
            }

        }
    }


}