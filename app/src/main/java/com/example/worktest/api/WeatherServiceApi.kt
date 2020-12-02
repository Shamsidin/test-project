package com.example.worktest.api

import com.example.worktest.model.WeatherResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServiceApi {

    @GET("data/2.5/weather")
    fun getWeather(
        @Query("id") id: Long = 498817,
        @Query("lang") lang: String = "ru",
        @Query("units") units: String = "metric",
    ): Single<WeatherResult>
}