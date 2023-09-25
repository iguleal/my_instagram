package com.example.myinstagram.register.data

import android.os.Handler
import android.os.Looper
import com.example.myinstagram.model.Database
import com.example.myinstagram.model.UserAuth
import com.example.myinstagram.register.RegisterEmail


class FakeRegisterEmailDataSource: RegisterEmailDataSource {
    override fun create(email: String, callback: RegisterEmailCallback) {

        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.userAuth.firstOrNull { email == it.email }

            if (userAuth == null ){
                callback.onSuccess()
            } else {
                callback.onFailure("usuário já cadastrado")
            }

            callback.onComplete()
        }, 2000)
    }
}