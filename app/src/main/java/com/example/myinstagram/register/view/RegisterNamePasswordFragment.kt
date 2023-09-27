package com.example.myinstagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myinstagram.R
import com.example.myinstagram.common.base.DependencyInjector
import com.example.myinstagram.common.util.TxtWatcher
import com.example.myinstagram.databinding.FragmentRegisterNamePasswordBinding
import com.example.myinstagram.register.RegisterNameAndPassword
import com.example.myinstagram.register.presentation.RegisterNameAndPasswordPresenter


class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password), RegisterNameAndPassword.View {

    private var binding: FragmentRegisterNamePasswordBinding? = null

    private var fragmentAttachListener: FragmentAttachListener? = null

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

    override fun goToWelcomeScreen(name: String) {
        fragmentAttachListener?.goToWelcomeScreen(name)
    }

    private val watcher = TxtWatcher {
        binding?.btnNextNameRegister?.isEnabled = binding?.editNameRegister?.text.toString().isNotEmpty()
                && binding?.editPasswordRegister?.text.toString().isNotEmpty()
                && binding?.editConfirmRegister?.text.toString().isNotEmpty()
    }

    companion object {
        const val KEY_EMAIL = "key_email"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        binding = null
    }

}