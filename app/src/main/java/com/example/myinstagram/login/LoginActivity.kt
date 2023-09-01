package com.example.myinstagram.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.myinstagram.databinding.ActivityLoginBinding
import com.example.myinstagram.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editEmailLogin.addTextChangedListener(watcher)
        binding.editPasswordLogin.addTextChangedListener(watcher)

        binding.btnEnter.setOnClickListener {
            binding.btnEnter.showProgress(true)
            binding.editEmailLoginInput.error = "Email Inválido"
            binding.editPasswordLoginInput.error = "Senha Inválida"

            Handler(Looper.getMainLooper()).postDelayed({
                binding.btnEnter.showProgress(false)
            },2000)
        }

        binding.txtHaveAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.btnEnter.isEnabled = s.toString().isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }
}