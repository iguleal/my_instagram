package com.example.myinstagram.register.data

interface RegisterEmailDataSource {
    fun create(email: String, callback: RegisterEmailCallback)
}