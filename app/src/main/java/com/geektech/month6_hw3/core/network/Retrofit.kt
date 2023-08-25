package com.geektech.month6_hw3.core.network

import com.geektech.month6_hw3.BuildConfig
import com.geektech.month6_hw3.data.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    var retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    var api = retrofit.create(ApiService::class.java)
}