package com.manohar.colaninfotechtask.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TodoDao {

    @Query("SELECT * from todo")
    fun getTodo() : LiveData<List<Todo>>

    @Insert
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}