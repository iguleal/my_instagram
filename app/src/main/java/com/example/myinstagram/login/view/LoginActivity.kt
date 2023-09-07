package com.example.myinstagram.login.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.myinstagram.common.util.TxtWatcher
import com.example.myinstagram.databinding.ActivityLoginBinding
import com.example.myinstagram.login.Login
import com.example.myinstagram.login.presentation.LoginPresenter
import com.example.myinstagram.register.RegisterActivity

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = LoginPresenter(this)

        with(binding){
            editEmailLogin.addTextChangedListener(watcher)
            editEmailLogin.addTextChangedListener(TxtWatcher{
                displayEmailFailure(null)
            })
            editPasswordLogin.addTextChangedListener(watcher)
            editPasswordLogin.addTextChangedListener(TxtWatcher{
                displayPasswordFailure(null)
            })

            btnEnter.setOnClickListener {
                presenter.login(binding.editEmailLogin.text.toString(),binding.editPasswordLogin.text.toString())
            }

            txtHaveAccount.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

    private val watcher = TxtWatcher {
        binding.btnEnter.isEnabled = binding.editEmailLogin.text.toString().isNotEmpty()
                && binding.editPasswordLogin.text.toString().isNotEmpty()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress(enabled: Boolean) {
        binding.btnEnter.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.editEmailLoginInput.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.editPasswordLoginInput.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        // OUTRA TELA
    }

    override fun onUserUnauthorized() {
        // MOSTRAR ALERTA
    }
}