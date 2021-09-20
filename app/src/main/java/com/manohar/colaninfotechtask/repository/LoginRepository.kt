package com.manohar.colaninfotechtask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.manohar.colaninfotechtask.repository.model.LoginModel
import com.manohar.colaninfotechtask.repository.networking.LoginApi
import retrofit2.Response

class LoginRepository(private val loginApi: LoginApi) {

    private val loginResultMutable = MutableLiveData<LoginModel>()

    val loginResultLiveData : LiveData<LoginModel>
    get() = loginResultMutable

    suspend fun loginWithDetails(email : String, password: String){
        val loginResult = loginApi.loginUser(email, password)
        if (loginResult.body() != null){
            loginResultMutable.postValue(loginResult.body())
        }

//        loginResult.enqueue(object : Callback<List<Post?>?>() {
//            fun onResponse(call: Call<List<Post?>?>?, response: Response<List<Post?>?>) {
//                Log.e(TAG, "onResponse: " + response.body())
//            }
//
//            fun onFailure(call: Call<List<Post?>?>?, t: Throwable) {
//                Log.e(TAG, "onFailure: " + t.localizedMessage)
//            }
//        })
    }
}