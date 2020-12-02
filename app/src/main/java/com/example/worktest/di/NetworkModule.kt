package com.example.worktest.di

import com.example.worktest.api.ImageServiceApi
import com.example.worktest.api.WeatherServiceApi
import com.example.worktest.util.Const
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideImageServiceApi(get()) }
    single { provideWeatherServiceApi(get()) }
}

private fun provideImageServiceApi(gson: Gson): ImageServiceApi {
    return Retrofit.Builder()
        .baseUrl(Const.IMAGE_SERVICE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ImageServiceApi::class.java)
}

private fun provideWeatherServiceApi(gson: Gson): WeatherServiceApi {
    return Retrofit.Builder()
        .baseUrl(Const.WEATHER_SERVICE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getOkHttpClient())
        .build()
        .create(WeatherServiceApi::class.java)
}

private fun getOkHttpClient(): OkHttpClient {
    val requestInterceptor = Interceptor { chain ->
        val original = chain.request()
        val originalUrl = original.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter("appid", "c35880b49ff95391b3a6d0edd0c722eb")
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    return OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .build()
}