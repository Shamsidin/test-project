package com.example.worktest.model

import com.google.gson.annotations.SerializedName

data class WeatherResult(
    @SerializedName("name")
    val cityName: String?,

    @SerializedName("main")
    val temperature: Temperature?,

    @SerializedName("clouds")
    val clouds: Clouds?
)