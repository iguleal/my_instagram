package com.example.myinstagram.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myinstagram.R
import com.example.myinstagram.common.CustomDialog


class RegisterPhotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val customDialog = CustomDialog(requireContext())

        customDialog.setTitle(R.string.define_photo_profile)

        customDialog.addButton( R.string.photo, R.string.gallery){
            when(it.id){
                R.string.photo -> {

                }
                R.string.gallery -> {

                }
            }

        }

        customDialog.show()
    }

}