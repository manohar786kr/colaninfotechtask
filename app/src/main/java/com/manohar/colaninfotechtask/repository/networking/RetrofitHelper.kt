package com.manohar.colaninfotechtask.repository.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASEURL = "https://reqres.in/api/"

    fun getInstance() : Retrofit{
        return Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}