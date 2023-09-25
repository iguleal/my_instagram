package com.example.myinstagram.register.data

class RegisterEmailRepository(private val datasource: RegisterEmailDataSource) {

    fun create(email:String, callback: RegisterEmailCallback){
        datasource.create(email, callback)
    }

}