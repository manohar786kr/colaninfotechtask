package com.manohar.colaninfotechtask.repository

import androidx.lifecycle.LiveData
import com.manohar.colaninfotechtask.db.Todo
import com.manohar.colaninfotechtask.db.TodoDao

class TodoRepository(val todoDao: TodoDao) {

    fun getTodo() : LiveData<List<Todo>>{
        return todoDao.getTodo()
    }

    suspend fun insertTodo(todo: Todo){
        todoDao.insertTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
}