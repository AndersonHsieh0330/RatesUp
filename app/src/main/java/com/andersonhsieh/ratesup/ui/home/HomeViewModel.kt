package com.andersonhsieh.ratesup.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andersonhsieh.ratesup.data.Repository
import com.andersonhsieh.ratesup.model.APIResponseObject
import com.andersonhsieh.ratesup.model.ExtractedCurrencyHistoryData
import com.andersonhsieh.ratesup.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(val repository: Repository) : ViewModel() {

    private val currencyData = MutableLiveData<ExtractedCurrencyHistoryData>()


    fun getLiveData():LiveData<ExtractedCurrencyHistoryData>{
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
        repository.getData(fromCurrency, fromDate, toDate)
            .enqueue(object : Callback<APIResponseObject> {
                override fun onResponse(
                    call: Call<APIResponseObject>,
                    response: Response<APIResponseObject>
                ) {

                    with(response.body()) {
                        Log.d(Constants.loggingTag, "onResponse: $toDate")
                        val currentValue = this?.data?.get(toDate)?.get(toCurrency) ?: 0.0
                        val oneMonthAgoValue =
                            this?.data?.get(oneMonthAgoTimeStamp)?.get(toCurrency) ?: 0.0;
                        val twoMonthsAgoValue =
                            this?.data?.get(twoMonthsAgoTimStamp)?.get(toCurrency) ?: 0.0;
                        val threeMonthsAgoValue = this?.data?.get(fromDate)?.get(toCurrency) ?: 0.0;

                        currencyData.value = ExtractedCurrencyHistoryData(
                            toDate,
                            currentValue,
                            oneMonthAgoTimeStamp,
                            oneMonthAgoValue,
                            twoMonthsAgoTimStamp,
                            twoMonthsAgoValue,
                            fromDate,
                            threeMonthsAgoValue
                        )

                    }

                }

                override fun onFailure(call: Call<APIResponseObject>, t: Throwable) {
                    Log.d(Constants.loggingTag, "GET request sent, response failed")
                }

            })
    }
}