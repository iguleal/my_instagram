package com.example.myinstagram.common.base

import com.example.myinstagram.login.data.FakeDataSource
import com.example.myinstagram.login.data.LoginRepository
import com.example.myinstagram.register.data.FakeRegisterEmailDataSource
import com.example.myinstagram.register.data.RegisterRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(FakeRegisterEmailDataSource())
    }
}