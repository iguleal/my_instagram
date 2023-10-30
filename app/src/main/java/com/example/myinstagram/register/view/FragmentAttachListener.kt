package com.example.myinstagram.register.view

interface FragmentAttachListener {
    fun goToNameAndPasswordScreen(email:String)
    fun goToWelcomeScreen(name:String)
    fun goToPhotoScreen()
    fun goToMainScreen()
    fun gotoGalleryScreen()
    fun goToCameraScreen()
}