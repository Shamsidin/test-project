package com.example.worktest.model

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("temp")
    val temp: Double?,

    @SerializedName("humidity")
    val humidity: Double?
)