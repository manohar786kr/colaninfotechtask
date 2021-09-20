package com.manohar.colaninfotechtask.util

import android.content.Context
import android.content.SharedPreferences

class UserSessionManager(val context: Context) {

    private var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor? = null

    init {
        sharedPreferences = context.getSharedPreferences("task", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun saveUserDetails(token: String){
        editor!!.putString("token", token)
        editor!!.putBoolean("isUserLoggedIn", true)
        editor!!.commit()
    }

    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isUserLoggedIn", false)
    }
}