package com.manohar.colaninfotechtask.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import com.manohar.colaninfotechtask.R
import com.manohar.colaninfotechtask.util.UserSessionManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //val sharedPreferences = getSharedPreferences("task", Context.MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({
              if (UserSessionManager(this).isUserLoggedIn()){
                  Log.d("SplashActivit", "onCreate isUserLoggedIn : "+UserSessionManager(this).isUserLoggedIn())
                  startActivity(Intent(this,HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
              }else{ startActivity(Intent(this,LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)) }
        },2000)
    }
}