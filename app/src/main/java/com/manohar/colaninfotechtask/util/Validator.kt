package com.manohar.colaninfotechtask.util

import android.widget.EditText
import com.manohar.colaninfotechtask.R

object Validator {
    fun validateEmptyCheckOfEditText(value : String) : Boolean{
        return !value.isEmpty()
    }
}