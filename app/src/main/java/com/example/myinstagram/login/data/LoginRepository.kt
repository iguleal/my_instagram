package com.example.myinstagram.login.data

class LoginRepository(private val datasource: LoginDataSource) {

    fun login(email:String, password: String, callback: LoginCallback){
        datasource.login(email,password,callback)
    }

}