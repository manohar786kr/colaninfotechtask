package com.manohar.colaninfotechtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manohar.colaninfotechtask.repository.TodoRepository

class HomeViewModelFactory(private val todoRepository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(todoRepository) as T
    }
}