package com.example.myinstagram.login.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.myinstagram.common.util.TxtWatcher
import com.example.myinstagram.databinding.ActivityLoginBinding
import com.example.myinstagram.login.Login
import com.example.myinstagram.register.RegisterActivity

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            editEmailLogin.addTextChangedListener(watcher)
            editPasswordLogin.addTextChangedListener(watcher)

            btnEnter.setOnClickListener {
                // CHAMAR O PRESENTER
            }

            txtHaveAccount.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }
    private val watcher = TxtWatcher {
        binding.btnEnter.isEnabled = it.isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding.btnEnter.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int) {
        binding.editEmailLoginInput.error = getString(emailError)
    }

    override fun displayPasswordFailure(passwordError: Int) {
        binding.editPasswordLoginInput.error = getString(passwordError)
    }

    override fun onUserAuthenticated() {
        // OUTRA TELA
    }

    override fun onUserUnauthorized() {
        // MOSTRAR ALERTA
    }
}