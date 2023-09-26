package com.example.myinstagram.register.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.myinstagram.R
import com.example.myinstagram.common.base.DependencyInjector
import com.example.myinstagram.common.util.TxtWatcher
import com.example.myinstagram.databinding.FragmentRegisterNamePasswordBinding
import com.example.myinstagram.register.RegisterNameAndPassword
import com.example.myinstagram.register.presentation.RegisterNameAndPasswordPresenter
import java.lang.IllegalArgumentException


class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password), RegisterNameAndPassword.View {

    private var binding: FragmentRegisterNamePasswordBinding? = null

    override lateinit var presenter: RegisterNameAndPassword.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterNamePasswordBinding.bind(view)

        presenter = RegisterNameAndPasswordPresenter(this, DependencyInjector.registerEmailRepository())

        val email = arguments?.getString(KEY_EMAIL) ?: throw IllegalArgumentException("email not found")

        binding?.let {
            with(it){
                txtAlreadyHasAccount.setOnClickListener {
                    activity?.finish()
                }

                btnNextNameRegister.setOnClickListener {
                    presenter.create(
                        email,
                        editNameRegister.text.toString(),
                        editPasswordRegister.text.toString(),
                        editConfirmRegister.text.toString()
                    )
                }

                editNameRegister.addTextChangedListener(watcher)
                editNameRegister.addTextChangedListener(TxtWatcher{
                    displayNameFailure(null)
                })

                editPasswordRegister.addTextChangedListener(watcher)
                editPasswordRegister.addTextChangedListener(TxtWatcher{
                    displayPasswordFailure(null)
                })

                editConfirmRegister.addTextChangedListener(watcher)
                editConfirmRegister.addTextChangedListener(TxtWatcher{
                    displayPasswordFailure(null)
                })
            }
        }

        Log.i("teste", email.toString())
    }


    override fun showProgress(enabled: Boolean) {
        binding?.btnNextNameRegister?.showProgress(enabled)
    }

    override fun displayNameFailure(nameError: Int?) {
        binding?.editNameRegisterInput?.error = nameError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding?.editPasswordRegisterInput?.error = passwordError?.let { getString(it) }
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateSuccess(name: String) {
        // abrir bem vindo
    }

    private val watcher = TxtWatcher{
        binding?.btnNextNameRegister?.isEnabled = binding?.editNameRegister?.text.toString().isNotEmpty()
                && binding?.editPasswordRegister?.text.toString().isNotEmpty()
                && binding?.editConfirmRegister?.text.toString().isNotEmpty()
    }

    companion object {
        const val KEY_EMAIL = "key_email"
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        binding = null
    }

}