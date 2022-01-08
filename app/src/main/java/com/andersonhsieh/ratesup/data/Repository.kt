package com.andersonhsieh.ratesup.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.andersonhsieh.ratesup.data.network_requests.RetrofitInstance
import com.andersonhsieh.ratesup.model.APIResponseObject
import com.andersonhsieh.ratesup.util.Constants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import java.lang.StringBuilder
import java.time.LocalDate

class Repository {
    companion object {
        @Volatile
        private var instance: Repository? = null;
        fun getInstance(): Repository {
            //make a copy to prevent instance getting changed when returned to where getInstance() is getting called
            val tempInstance = instance;
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                return Repository()
            }
        }
    }

    fun getData(baseCurrency: String): Call<APIResponseObject> {
        val today = LocalDate.now()
        val toDate = today.toString()
        val fromDate = setToFirstDayOfMonth(today.minusMonths(3).toString())
        //https://www.youtube.com/watch?v=sBCE_hOFnQU&ab_channel=Stevdza-San 5:49
        Log.d(Constants.loggingTag, "Repository -> getData: $fromDate ~ $toDate")
        return RetrofitInstance.apiAccessPoint.getCurrencyData(baseCurrency, fromDate, toDate);
    }

    fun setToFirstDayOfMonth(yyyy_MM_dd: String): String {
        val builder = StringBuilder(yyyy_MM_dd)

        //the end index is excluded
        builder.delete(builder.lastIndex - 1, builder.lastIndex + 1)
        builder.append("01")

        return builder.toString()
    }


}