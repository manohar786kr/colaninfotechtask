package com.manohar.colaninfotechtask.util

import com.manohar.colaninfotechtask.db.Todo

interface TodoListAdapterClickListenerInterface {
    fun onItemDelete(todo: Todo)
}