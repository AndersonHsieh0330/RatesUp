package com.andersonhsieh.ratesup.data

import android.util.Log
import com.andersonhsieh.ratesup.data.network_requests.CurrencyDataAPI
import com.andersonhsieh.ratesup.model.APIResponseObject
import com.andersonhsieh.ratesup.util.Constants
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
//Repository should stay alive as long as app is alive
class Repository @Inject constructor(val currencyApiAccessPoint:CurrencyDataAPI) {
//    companion object {
//        @Volatile
//        private var instance: Repository? = null;
//        fun getInstance(): Repository {
//            //make a copy to prevent instance getting changed when returned to where getInstance() is getting called
//            val tempInstance = instance;
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                return Repository()
//            }
//        }
//    }

    fun getData(baseCurrency: String, fromDate:String, toDate:String): Call<APIResponseObject> {

        //https://www.youtube.com/watch?v=sBCE_hOFnQU&ab_channel=Stevdza-San 5:49
        Log.d(Constants.loggingTag, "Repository -> getData: $fromDate ~ $toDate")
        return currencyApiAccessPoint.getCurrencyData(baseCurrency, fromDate, toDate);
    }




}