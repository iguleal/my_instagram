package com.example.myinstagram.common.view

import android.net.Uri

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myinstagram.R
import com.example.myinstagram.databinding.FragmentImageCropperBinding

class CropperImageFragment : Fragment(R.layout.fragment_image_cropper) {
    private var binding: FragmentImageCropperBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageCropperBinding.bind(view)

        val uri = when{
            SDK_INT >= 33 -> arguments?.getParcelable(KEY_URI, Uri::class.java)
            else -> arguments?.getParcelable(KEY_URI)
        }

        binding?.let {
            with(it) {
                containerCropper.setAspectRatio(1,1)
                containerCropper.setFixedAspectRatio(true)
                containerCropper.setImageUriAsync(uri)

                btnCancelCropper.setOnClickListener {
                    parentFragmentManager.popBackStack()
                }

                btnSaveCropper.setOnClickListener {
                    ///////////////////////////////PAREI AQUI //////////////////////////////////////////////
                }
            }
        }
    }

    companion object {
        const val KEY_URI = "key_uri"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}