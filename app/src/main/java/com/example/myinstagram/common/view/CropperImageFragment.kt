package com.example.myinstagram.common.view

import android.net.Uri

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.myinstagram.R
import com.example.myinstagram.databinding.FragmentImageCropperBinding
import java.io.File

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
                    val dir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    if (dir != null){
                        val uriToSaved = Uri.fromFile(File(dir.path, System.currentTimeMillis().toString() + ".jpeg"))
                        containerCropper.saveCroppedImageAsync(uriToSaved)
                    }
                }

                containerCropper.setOnCropImageCompleteListener { view, result ->
                    setFragmentResult("cropKey", bundleOf(KEY_URI to result.uri))
                    parentFragmentManager.popBackStack()
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