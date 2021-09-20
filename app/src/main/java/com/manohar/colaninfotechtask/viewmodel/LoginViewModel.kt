package com.manohar.colaninfotechtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manohar.colaninfotechtask.repository.LoginRepository
import com.manohar.colaninfotechtask.repository.model.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    fun loginWithDetails(email : String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.loginWithDetails(email, password)
        }
    }

    val loginResult : LiveData<LoginModel>
    get() = loginRepository.loginResultLiveData
}