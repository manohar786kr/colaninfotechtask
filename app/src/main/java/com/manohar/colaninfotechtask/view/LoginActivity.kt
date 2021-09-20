package com.manohar.colaninfotechtask.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.manohar.colaninfotechtask.R
import com.manohar.colaninfotechtask.databinding.ActivityLoginBinding
import com.manohar.colaninfotechtask.repository.LoginRepository
import com.manohar.colaninfotechtask.repository.model.LoginModel
import com.manohar.colaninfotechtask.repository.networking.LoginApi
import com.manohar.colaninfotechtask.repository.networking.RetrofitHelper
import com.manohar.colaninfotechtask.util.NetworkState
import com.manohar.colaninfotechtask.util.UserSessionManager
import com.manohar.colaninfotechtask.util.Validator
import com.manohar.colaninfotechtask.viewmodel.LoginViewModel
import com.manohar.colaninfotechtask.viewmodel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    //private lateinit var activityLoginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityLoginBinding.root)

        val apiLogin = RetrofitHelper.getInstance().create(LoginApi::class.java)
        val repository = LoginRepository(apiLogin)

        loginViewModel = ViewModelProvider(this,LoginViewModelFactory(repository)).get(LoginViewModel::class.java)

        //loginViewModel.loginWithDetails("eve.holt@reqres.in","cityslicka")

        activityLoginBinding.loginBtn.setOnClickListener {
            if (NetworkState.isNetworkAvailable(this)){
                if (Validator.validateEmptyCheckOfEditText(activityLoginBinding.emailEt.text.toString())){
                    if (Validator.validateEmptyCheckOfEditText(activityLoginBinding.passwordEt.text.toString())){
                        loginViewModel.loginWithDetails(
                            activityLoginBinding.emailEt.text.toString().trim(),
                            activityLoginBinding.passwordEt.text.toString().trim()
                        )
                    }else{ showEditTextError(activityLoginBinding.passwordEt) }
                }else{ showEditTextError(activityLoginBinding.emailEt) }
            }
        }

        loginViewModel.loginResult.observe(this, Observer {
            //Log.d("LoginActivity", "onCreate: "+it.token)
            UserSessionManager(this).saveUserDetails(it.token)
            startActivity(
                Intent(this,HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK))

        })
    }

    private fun showEditTextError(editText: EditText){
        editText.requestFocus()
        editText.setError(resources.getString(R.string.enter_valid_details))
    }
}