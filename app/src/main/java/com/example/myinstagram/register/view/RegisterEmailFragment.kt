package com.example.myinstagram.register.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.myinstagram.R
import com.example.myinstagram.common.base.DependencyInjector
import com.example.myinstagram.common.util.TxtWatcher
import com.example.myinstagram.databinding.FragmentRegisterEmailBinding
import com.example.myinstagram.register.RegisterEmail
import com.example.myinstagram.register.presentation.RegisterEmailPresenter


class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {

    private var binding : FragmentRegisterEmailBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override lateinit var presenter: RegisterEmail.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)

        presenter = RegisterEmailPresenter(this, DependencyInjector.registerEmailRepository())

        binding?.let {
            with(it){
                txtAlreadyHasAccount.setOnClickListener {
                    activity?.finish()
                }
                editEmailRegister.addTextChangedListener(watcher)
                editEmailRegister.addTextChangedListener(TxtWatcher{
                    displayEmailFailure(null)
                })
                btnNextRegister.setOnClickListener {
                    presenter.create(editEmailRegister.text.toString())
                }
            }
        }
    }

    private val watcher = TxtWatcher{
        binding?.btnNextRegister?.isEnabled = binding?.editEmailRegister?.text.toString().isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.btnNextRegister?.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding?.editEmailRegisterInput?.error = emailError?.let { getString(it) }
    }

    override fun onEmailFailure(message: String) {
        binding?.editEmailRegisterInput?.error = message
    }

    override fun goToNameAndPasswordScreen(email: String) {
        fragmentAttachListener?.goToNameAndPasswordScreen(email)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListener = null
        presenter.onDestroy()
        super.onDestroy()
    }

}