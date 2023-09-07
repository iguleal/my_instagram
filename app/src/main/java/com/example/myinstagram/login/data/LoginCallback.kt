package com.example.myinstagram.login.data

interface LoginCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}