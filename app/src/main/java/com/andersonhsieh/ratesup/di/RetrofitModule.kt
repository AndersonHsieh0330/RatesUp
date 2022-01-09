package com.andersonhsieh.ratesup.di

import com.andersonhsieh.ratesup.data.network_requests.CurrencyDataAPI
import com.andersonhsieh.ratesup.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
     fun retrofit():Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(Constants.currencyAPIBaseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        }

    @Singleton
    @Provides
    //this function is injected by the above function that returns Retrofit as well
    fun apiAccessPoint(retrofit:Retrofit): CurrencyDataAPI {
        return retrofit.create(CurrencyDataAPI::class.java)
    }
}