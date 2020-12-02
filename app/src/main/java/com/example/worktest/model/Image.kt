package com.example.worktest.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("author")
    val author: String?,

    @SerializedName("download_url")
    val url: String?
)