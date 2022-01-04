package com.andersonhsieh.ratesup.data

import com.andersonhsieh.ratesup.data.network_requests.RetrofitInstance
import com.andersonhsieh.ratesup.model.APIResponseObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit

class Repository {
    companion object{
        @Volatile
        private var instance:Repository? = null;
        fun getInstance():Repository? {
            //make a copy to prevent instance getting changed when returned to where getInstance() is getting called
            val tempInstance = instance;
            if(tempInstance == null){
                return tempInstance
            }
            synchronized(this){
                return Repository()
            }
        }
    }

     fun getData(): Call<APIResponseObject> {
        //https://www.youtube.com/watch?v=sBCE_hOFnQU&ab_channel=Stevdza-San 5:49
         return RetrofitInstance.apiAccessPoint.getCurrencyData();
     }


}