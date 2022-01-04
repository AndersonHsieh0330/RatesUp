package com.andersonhsieh.ratesup.data.network_requests

import com.andersonhsieh.ratesup.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.currencyAPIBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiAccessPoint by lazy{
        retrofit.create(CurrencyDataAPI::class.java)
    }
}