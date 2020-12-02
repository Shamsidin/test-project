package com.example.worktest.api

import com.example.worktest.model.Image
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageServiceApi {

    @GET("v2/list")
    fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Single<List<Image>>
}
