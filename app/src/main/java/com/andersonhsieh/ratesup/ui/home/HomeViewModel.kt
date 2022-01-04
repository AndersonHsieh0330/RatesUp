package com.andersonhsieh.ratesup.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andersonhsieh.ratesup.data.Repository
import com.andersonhsieh.ratesup.model.APIResponseObject
import com.andersonhsieh.ratesup.model.CurrencyData
import com.andersonhsieh.ratesup.model.ExtractedCurrencyHistoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(val repository:Repository) : ViewModel() {

    private val currencyData = MutableLiveData<ExtractedCurrencyHistoryData>()


    fun getData(){
        repository.getData().enqueue(object : Callback<APIResponseObject> {
            override fun onResponse(
                call: Call<APIResponseObject>,
                response: Response<APIResponseObject>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<APIResponseObject>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}