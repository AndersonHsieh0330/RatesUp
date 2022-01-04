package com.andersonhsieh.ratesup.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class APIResponseObject(
    //ignore the "query" JSON object
    @SerializedName("data")
    val data:Map<String, CurrencyData>
) {
}