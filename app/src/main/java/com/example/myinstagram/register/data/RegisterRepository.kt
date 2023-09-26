package com.example.myinstagram.register.data

class RegisterRepository(private val datasource: RegisterDataSource) {

    fun create(email: String, callback: RegisterCallback) {
        datasource.create(email, callback)
    }

    fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        datasource.create(email, name, password, callback)
    }

}