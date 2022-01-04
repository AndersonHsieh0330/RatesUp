package com.andersonhsieh.ratesup.model


import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate


data class CurrencyData(
    @SerializedName("IRR")
    val IRR: Double,
    @SerializedName("RWF")
    val RWF: Double,
    @SerializedName("BDT")
    val BDT: Double,
    @SerializedName("KYD")
    val KYD: Double,
    @SerializedName("AED")
    val AED: Double,
    @SerializedName("GEL")
    val GEL: Double,
    @SerializedName("DOP")
    val DOP: Double,
    @SerializedName("GBP")
    val GBP: Double,
    @SerializedName("UZS")
    val UZS: Double,
    @SerializedName("MVR")
    val MVR: Double,
    @SerializedName("KZT")
    val KZT: Double,
    @SerializedName("LTC")
    val LTC: Double,
    @SerializedName("HRV")
    val HRV: Double,
    @SerializedName("NZD")
    val NZD: Double,
    @SerializedName("CZK")
    val CZK: Double,
    @SerializedName("ZAR")
    val ZAR: Double,
    @SerializedName("DJF")
    val DJF: Double,
    @SerializedName("MXN")
    val MXN: Double,
    @SerializedName("PKR")
    val PKR: Double,
    @SerializedName("INR")
    val INR: Double,
    @SerializedName("AZN")
    val AZN: Double,
    @SerializedName("SDG")
    val SDG: Double,
    @SerializedName("BRL")
    val BRL: Double,
    @SerializedName("ARS")
    val ARS: Double,
    @SerializedName("TRY")
    val TRY: Double,
    @SerializedName("SLL")
    val SLL: Double,
    @SerializedName("XPF")
    val XPF: Double,
    @SerializedName("STD")
    val STD: Double,
    @SerializedName("BOB")
    val BOB: Double,
    @SerializedName("CLP")
    val CLP: Double,
    @SerializedName("KGS")
    val KGS: Double,
    @SerializedName("MWK")
    val MWK: Double,
    @SerializedName("XOF")
    val XOF: Double,
    @SerializedName("RSD")
    val RSD: Double,
    @SerializedName("BND")
    val BND: Double,
    @SerializedName("MGA")
    val MGA: Double,
    @SerializedName("RUB")
    val RUB: Double,
    @SerializedName("IQD")
    val IQD: Double,
    @SerializedName("CUC")
    val CUC: Double,
    @SerializedName("NOK")
    val NOK: Double,
    @SerializedName("EUR")
    val EUR: Double,
    @SerializedName("AOA")
    val AOA: Double,
    @SerializedName("JPY")
    val JPY: Double,
    @SerializedName("SRD")
    val SRD: Double,
    @SerializedName("CNY")
    val CNY: Double,
    @SerializedName("BGN")
    val BGN: Double,
    @SerializedName("MOP")
    val MOP: Double,
    @SerializedName("JMD")
    val JMD: Double,
    @SerializedName("QAR")
    val QAR: Double,
    @SerializedName("NIO")
    val NIO: Double,
    @SerializedName("BIF")
    val BIF: Double,
    @SerializedName("XRP")
    val XRP: Double,
    @SerializedName("UAH")
    val UAH: Double,
    @SerializedName("OMR")
    val OMR: Double,
    @SerializedName("MNT")
    val MNT: Double,
    @SerializedName("URY")
    val URY: Double,
    @SerializedName("CDF")
    val CDF: Double,
    @SerializedName("MMK")
    val MMK: Double,
    @SerializedName("SYP")
    val SYP: Double,
    @SerializedName("AMD")
    val AMD: Double,
    @SerializedName("JOD")
    val JOD: Double,
    @SerializedName("GNF")
    val GNF: Double,
    @SerializedName("ETH")
    val ETH: Double,
    @SerializedName("PGK")
    val PGK: Double,
    @SerializedName("BHD")
    val BHD: Double,
    @SerializedName("HNL")
    val HNL: Double,
    @SerializedName("UGX")
    val UGX: Double,
    @SerializedName("KRW")
    val KRW: Double,
    @SerializedName("DKK")
    val DKK: Double,
    @SerializedName("LSL")
    val LSL: Double,
    @SerializedName("MKD")
    val MKD: Double,
    @SerializedName("PAB")
    val PAB: Double,


    @SerializedName("DZD")
    val DZD: Double,
    @SerializedName("TWD")
    val TWD: Double,
    @SerializedName("SAR")
    val SAR: Double,
    @SerializedName("GYD")
    val GYD: Double,
    @SerializedName("GHS")
    val GHS: Double,
    @SerializedName("PYG")
    val PYG: Double,
    @SerializedName("KES")
    val KES: Double,
    @SerializedName("AFN")
    val AFN: Double,
    @SerializedName("GTQ")
    val GTQ: Double,
    @SerializedName("PHP")
    val PHP: Double,
    @SerializedName("MUR")
    val MUR: Double,
    @SerializedName("LBP")
    val LBP: Double,
    @SerializedName("LKR")
    val LKR: Double,
    @SerializedName("SZL")
    val SZL: Double,
    @SerializedName("ISK")
    val ISK: Double,
    @SerializedName("BIH")
    val BIH: Double,
    @SerializedName("ERN")
    val ERN: Double,
    @SerializedName("TZS")
    val TZS: Double,
    @SerializedName("SSP")
    val SSP: Double,
    @SerializedName("LYD")
    val LYD: Double,
    @SerializedName("EGP")
    val EGP: Double,
    @SerializedName("COP")
    val COP: Double,
    @SerializedName("CAD")
    val CAD: Double,
    @SerializedName("SCR")
    val SCR: Double,
    @SerializedName("VND")
    val VND: Double,
    @SerializedName("TTD")
    val TTD: Double,
    @SerializedName("TMT")
    val TMT: Double,
    @SerializedName("ETB")
    val ETB: Double,
    @SerializedName("RON")
    val RON: Double,
    @SerializedName("ALL")
    val ALL: Double,
    @SerializedName("SEK")
    val SEK: Double,
    @SerializedName("FJD")
    val FJD: Double,
    @SerializedName("MZN")
    val MZN: Double,
    @SerializedName("BWP")
    val BWP: Double,
    @SerializedName("BYR")
    val BYR: Double,
    @SerializedName("BSD")
    val BSD: Double,
    @SerializedName("CVE")
    val CVE: Double,
    @SerializedName("LRD")
    val LRD: Double,
    @SerializedName("AUD")
    val AUD: Double,
    @SerializedName("IDR")
    val IDR: Double,
    @SerializedName("YER")
    val YER: Double,
    @SerializedName("MYR")
    val MYR: Double,
    @SerializedName("LAK")
    val LAK: Double,
    @SerializedName("SOS")
    val SOS: Double,
    @SerializedName("TJS")
    val TJS: Double,
    @SerializedName("MDL")
    val MDL: Double,
    @SerializedName("KMF")
    val KMF: Double,
    @SerializedName("ILS")
    val ILS: Double,
    @SerializedName("SVC")
    val SVC: Double,
    @SerializedName("SGD")
    val SGD: Double,
    @SerializedName("THB")
    val THB: Double,
    @SerializedName("HTG")
    val HTG: Double,
    @SerializedName("TND")
    val TND: Double,
    @SerializedName("CHF")
    val CHF: Double,
    @SerializedName("BTC")
    val BTC: Double,
    @SerializedName("NAD")
    val NAD: Double,
    @SerializedName("GMD")
    val GMD: Double,
    @SerializedName("NGN")
    val NGN: Double,
    @SerializedName("MAD")
    val MAD: Double,
    @SerializedName("CRC")
    val CRC: Double,
    @SerializedName("PEN")
    val PEN: Double,
    @SerializedName("HUF")
    val HUF: Double,
    @SerializedName("NPR")
    val NPR: Double,
    @SerializedName("XAF")
    val XAF: Double,
    @SerializedName("HKD")
    val HKD: Double,
    @SerializedName("PLN")
    val PLN: Double,
    @SerializedName("KHR")
    val KHR: Double,
    ){




}