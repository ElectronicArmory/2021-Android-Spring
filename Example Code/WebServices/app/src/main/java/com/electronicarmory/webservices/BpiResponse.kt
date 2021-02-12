package com.electronicarmory.webservices

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BpiResponse(
    val bpi: BpiObject
)


data class BpiObject(val USD: BitcoinPrice)

class BitcoinPrice(
    val code: String,
    val symbol: String? = null,
    val rate: String,
    val description: String,

    @SerializedName("rate_float")
    val rateFloat: Float
)