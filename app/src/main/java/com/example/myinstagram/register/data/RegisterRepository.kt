package com.example.myinstagram.register.data

import android.net.Uri

class RegisterRepository(private val datasource: RegisterDataSource) {

    fun create(email: String, callback: RegisterCallback) {
        datasource.create(email, callback)
    }

    fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        datasource.create(email, name, password, callback)
    }

    fun updateUser(photoUri: Uri, callback: RegisterCallback){
        datasource.updateUser(photoUri, callback)
    }

}