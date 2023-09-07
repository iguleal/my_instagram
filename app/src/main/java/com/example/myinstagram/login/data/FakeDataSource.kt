package com.example.myinstagram.login.data

import android.os.Handler
import android.os.Looper


class FakeDataSource: LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            if (email == "a@a.com" && password == "12345678"){
                callback.onSuccess()
            } else {
                callback.onFailure("usuário nao encontrado")
            }
            callback.onComplete()

        }, 2000)
    }
}