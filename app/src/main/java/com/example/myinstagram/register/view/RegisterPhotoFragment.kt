package com.example.myinstagram.register.view

import android.content.Context
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.example.myinstagram.R
import com.example.myinstagram.common.base.DependencyInjector
import com.example.myinstagram.common.view.CropperImageFragment.Companion.KEY_URI
import com.example.myinstagram.common.view.CustomDialog
import com.example.myinstagram.databinding.FragmentRegisterPhotoBinding
import com.example.myinstagram.register.RegisterPhoto
import com.example.myinstagram.register.presentation.RegisterPhotoPresenter


class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo), RegisterPhoto.View {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override lateinit var presenter: RegisterPhoto.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropKey"){ requestKey, bundle ->
            val uri = when{
                SDK_INT >= 33 -> bundle.getParcelable(KEY_URI, Uri::class.java)
                else -> bundle.getParcelable(KEY_URI)
            }
            onCropImageResult(uri)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterPhotoBinding.bind(view)

        binding?.btnNextRegister?.isEnabled = true

        presenter = RegisterPhotoPresenter(this, DependencyInjector.registerEmailRepository())

        binding?.btnRegisterJump?.setOnClickListener {
            fragmentAttachListener?.goToMainScreen()
        }

        binding?.btnNextRegister?.setOnClickListener {
            createDialog()
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.btnNextRegister?.showProgress(enabled)
    }

    override fun onUpdateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onUpdateSuccess() {
        fragmentAttachListener?.goToMainScreen()
    }

    private fun createDialog() {
        CustomDialog(requireContext()).apply {
            setTitle(R.string.define_photo_profile)
            addButton(R.string.photo, R.string.gallery) {
                when (it.id) {
                    R.string.photo -> {
                        fragmentAttachListener?.goToCameraScreen()
                    }

                    R.string.gallery -> {
                        fragmentAttachListener?.gotoGalleryScreen()
                    }
                }
            }
            show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    private fun onCropImageResult(uri: Uri?){
        if (uri != null){
            binding?.imgRegisterProfile?.setImageURI(uri)
            presenter.updateUser(uri)
        }
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

}