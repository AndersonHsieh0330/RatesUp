package com.andersonhsieh.ratesup.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class APIResponseObject(
    @SerializedName("query")
    val query:QueryInfo,

    //Note: because the freecurrencyapi response JSON with dynamic keys
    //thus it's better to deserialize it with nested maps
    @SerializedName("data")
    val data:HashMap<String, HashMap<String,Double>>
) {
}