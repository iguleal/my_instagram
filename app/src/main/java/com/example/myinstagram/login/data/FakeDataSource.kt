package com.example.myinstagram.login.data

import android.os.Handler
import android.os.Looper
import com.example.myinstagram.model.Database
import com.example.myinstagram.model.UserAuth


class FakeDataSource: LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.userAuth.firstOrNull { email == it.email }

            when{
                userAuth == null ->{
                    callback.onFailure("usuário não encontrado")
                }
                userAuth.password != password -> {
                    callback.onFailure("senha está incorreta")
                }
                else -> {
                    Database.sessionAuth = userAuth
                    callback.onSuccess(userAuth)
                }
            }

            callback.onComplete()

        }, 2000)
    }
}