package com.example.myinstagram.common.extension

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyBoard(){
    val service = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}