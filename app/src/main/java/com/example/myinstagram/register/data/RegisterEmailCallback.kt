package com.example.myinstagram.register.data

import com.example.myinstagram.model.UserAuth

interface RegisterEmailCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}