package com.example.carapp.ui.user

import androidx.lifecycle.ViewModel

class SignActivityViewModel : ViewModel(){

    private var password = ""
    private var passwordConfirm = ""

    fun getPasswordFromSignIn(text: String) {
        password = text
    }

    fun getPasswordConfirmFromSignIn(text: String){
        passwordConfirm = text
    }

    fun comparePasswords(): Boolean{
        return password == passwordConfirm
    }
}