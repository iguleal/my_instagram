package com.example.myinstagram.register.view

import android.content.Context
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import com.example.myinstagram.R
import com.example.myinstagram.common.view.CropperImageFragment.Companion.KEY_URI
import com.example.myinstagram.common.view.CustomDialog
import com.example.myinstagram.databinding.FragmentRegisterPhotoBinding


class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

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
                    fragmentAttachListener?.goToCameraScreen()
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

    private fun onCropImageResult(uri: Uri?){
        if (uri != null){
            binding?.imgRegisterProfile?.setImageURI(uri)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}