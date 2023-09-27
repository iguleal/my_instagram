package com.example.myinstagram.register.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.myinstagram.R
import com.example.myinstagram.common.view.CustomDialog
import com.example.myinstagram.databinding.FragmentRegisterPhotoBinding


class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterPhotoBinding.bind(view)

        binding?.btnRegisterJump?.setOnClickListener {
            fragmentAttachListener?.goToMainScreen()
        }

        binding?.btnNextRegister?.setOnClickListener {
            createDialog()
        }

    }

    private fun createDialog() {
        val customDialog = CustomDialog(requireContext())

        customDialog.setTitle(R.string.define_photo_profile)

        customDialog.addButton(R.string.photo, R.string.gallery) {
            when (it.id) {
                R.string.photo -> {

                }

                R.string.gallery -> {
                    fragmentAttachListener?.gotoGalleryScreen()
                }
            }
        }
        customDialog.show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}