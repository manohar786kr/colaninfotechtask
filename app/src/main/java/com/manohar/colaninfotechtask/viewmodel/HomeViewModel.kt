package com.manohar.colaninfotechtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manohar.colaninfotechtask.db.Todo
import com.manohar.colaninfotechtask.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val todoRepository: TodoRepository) : ViewModel() {

    fun getTodo() : LiveData<List<Todo>> {
        return todoRepository.getTodo()
    }

    fun insertTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            todoRepository.insertTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            todoRepository.deleteTodo(todo)
        }
    }

}