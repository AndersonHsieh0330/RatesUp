package com.andersonhsieh.ratesup.model

class ExtractedCurrencyHistoryData(
    val currentTimeStamp:String,
    val currentValue:Double,
    val oneMonthAgoTimeStamp:String,
    val onMonthAgoValue:Double,
    val twoMonthsAgoTimeStamp:String,
    val twoMonthsAgoValue:Double,
    val threeMonthsAgoTimeStamp:String,
    val threeMonthsAgoValue:Double
){
}