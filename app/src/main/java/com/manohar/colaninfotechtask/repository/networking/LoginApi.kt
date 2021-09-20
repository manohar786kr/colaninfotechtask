package com.manohar.colaninfotechtask.repository.networking

import com.manohar.colaninfotechtask.repository.model.LoginModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(@Field("email") email : String,@Field("password")  password: String): Response<LoginModel>

//    @POST("login")
//    suspend fun loginUser(@Body body: Body): Response<LoginModel>
}