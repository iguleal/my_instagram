package com.example.myinstagram.login

import com.example.myinstagram.R

interface Login {

    interface View{
        fun showProgress(enabled:Boolean)
        fun displayEmailFailure(emailError: Int = R.string.invalid_email)
        fun displayPasswordFailure(passwordError: Int = R.string.invalid_password)
        fun onUserAuthenticated()
        fun onUserUnauthorized()
    }
}