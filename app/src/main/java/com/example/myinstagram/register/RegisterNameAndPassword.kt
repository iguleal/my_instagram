package com.example.myinstagram.register

import androidx.annotation.StringRes
import com.example.myinstagram.common.base.BasePresenter
import com.example.myinstagram.common.base.BaseView

interface RegisterNameAndPassword {

    interface Presenter : BasePresenter {
        fun create(email: String, name: String, password: String, confirm: String)
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayNameFailure(@StringRes nameError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
        fun onCreateFailure(message: String)
        fun goToWelcomeScreen(name: String)
    }
}