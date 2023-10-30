package com.example.myinstagram.register.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.example.myinstagram.model.Database
import com.example.myinstagram.model.Photo
import com.example.myinstagram.model.UserAuth
import java.util.UUID


class FakeRegisterEmailDataSource : RegisterDataSource {
    override fun create(email: String, callback: RegisterCallback) {

        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure("usuário já cadastrado")
            }

            callback.onComplete()
        }, 200)
    }

    override fun create(email: String, name: String, password: String, callback: RegisterCallback) {

        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth != null) {
                callback.onFailure("Usuário já cadastrado")
            } else {
                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password)

                val created = Database.usersAuth.add(newUser)

                if (created) {
                    Database.sessionAuth = newUser
                    callback.onSuccess()
                } else {
                    callback.onFailure("Erro interno no servidor")
                }

            }
            callback.onComplete()
        }, 200)
    }

    override fun updateUser(photoUri: Uri, callback: RegisterCallback) {

        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.sessionAuth

            if (userAuth == null) {
                callback.onFailure("Usuário não encontrado")
            } else {
                val newPhoto = Photo(userAuth.uuid, photoUri)

                val created = Database.photos.add(newPhoto)

                if (created) {
                    callback.onSuccess()
                } else {
                    callback.onFailure("Erro interno no servidor")
                }

            }
            callback.onComplete()
        }, 200)
    }
}
