package com.example.myinstagram.login.data

interface LoginDataSource {
    fun login(email: String, password: String, callback: LoginCallback)
}