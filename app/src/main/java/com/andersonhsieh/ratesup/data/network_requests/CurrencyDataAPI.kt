package com.andersonhsieh.ratesup.data.network_requests

import com.andersonhsieh.ratesup.model.APIResponseObject
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyDataAPI {

    @GET("historical?apikey=27c222a0-6b7c-11ec-8a35-2dde21434744&base_currency=USD&date_from=2021-10-01&date_to=2022-01-03")
    fun getCurrencyData(): Call<APIResponseObject>
}