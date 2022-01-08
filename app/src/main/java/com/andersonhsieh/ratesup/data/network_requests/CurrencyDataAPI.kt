package com.andersonhsieh.ratesup.data.network_requests

import com.andersonhsieh.ratesup.model.APIResponseObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyDataAPI {

    @GET("historical?apikey=27c222a0-6b7c-11ec-8a35-2dde21434744")
    fun getCurrencyData(@Query("base_currency")baseCurrency:String, @Query("date_from")fromCurrency:String, @Query("date_to")toCurrency:String): Call<APIResponseObject>
}