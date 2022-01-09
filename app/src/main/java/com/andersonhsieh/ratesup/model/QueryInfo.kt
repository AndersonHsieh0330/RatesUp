package com.andersonhsieh.ratesup.model

import com.google.gson.annotations.SerializedName

data class QueryInfo(
    @SerializedName("apiKey")
    val apiKey: String,
    @SerializedName("base_currency")
    val base_currency: String,
    @SerializedName("date_from")
    val date_from: String,
    @SerializedName("date_to")
    val date_to: String,
    @SerializedName("timeStamp")
    val timeStamp: Long
) {
}