package com.andersonhsieh.ratesup.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andersonhsieh.ratesup.data.Repository
import com.andersonhsieh.ratesup.model.APIResponseObject
import com.andersonhsieh.ratesup.model.CurrencyData
import com.andersonhsieh.ratesup.model.ExtractedCurrencyHistoryData
import com.andersonhsieh.ratesup.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(val repository:Repository) : ViewModel() {

    private val currencyData = MutableLiveData<ExtractedCurrencyHistoryData>()


    fun getData(fromCurrency:String, toCurrency:String){
        repository.getData(fromCurrency).enqueue(object : Callback<APIResponseObject> {
            override fun onResponse(
                call: Call<APIResponseObject>,
                response: Response<APIResponseObject>
            ) {
                Log.d(Constants.loggingTag, response.body().toString())

            }

            override fun onFailure(call: Call<APIResponseObject>, t: Throwable) {
                Log.d(Constants.loggingTag, "GET request sent, response failed")
            }

        })
    }
}