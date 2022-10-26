package com.example.carapp.ui.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.carapp.R
import com.example.carapp.database.UserDatabase
import com.example.carapp.model.User

class SignActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val btSignIn = findViewById<Button>(R.id.btSignIn)
        val etUserSignIn = findViewById<EditText>(R.id.etUserSignIn)
        val etNameSignIn = findViewById<EditText>(R.id.etNameSignIn)
        val etPasswordSignIn = findViewById<EditText>(R.id.etPasswordSignIn)
        val etPasswordConfirmSignIn = findViewById<EditText>(R.id.etConfirmPasswordSignIn)

        viewModel = LoginActivityViewModel(UserDatabase.factory.getInstance(this).getUserDao())

        btSignIn.setOnClickListener {

            etPasswordSignInCheck(
                etPasswordSignIn,
                etPasswordConfirmSignIn,
                etNameSignIn,
                etUserSignIn
            )
        }
    }

    private fun etPasswordSignInCheck(
        etPasswordSignIn: EditText,
        etPasswordConfirmSignIn: EditText,
        etNameSignIn: EditText,
        etUserSignIn: EditText
    ) {
        if (!etUserSignIn.text.isNullOrEmpty()) {
            if (!etNameSignIn.text.isNullOrEmpty()) {
                if (!etPasswordSignIn.text.isNullOrEmpty()) {
                    if (!etPasswordConfirmSignIn.text.isNullOrEmpty()) {

                        if (etPasswordSignIn.text.toString() == etPasswordConfirmSignIn.text.toString()) {
                            val userName = etUserSignIn.text.toString()
                            val name = etNameSignIn.text.toString()
                            val password = etPasswordSignIn.text.toString()
                            val user = User(name = name, user = userName, password = password)

                            viewModel.save(user)

                            startActivity(Intent(this, LoginActivity::class.java))

                        } else {
                            etPasswordConfirmSignIn.error = "Senhas não coincidem"
                            etPasswordSignIn.error = "Senhas não coincidem"
                        }
                    } else {
                        errorMessage(etPasswordConfirmSignIn)
                    }
                } else {
                    errorMessage(etPasswordSignIn)
                }
            } else {
                errorMessage(etNameSignIn)
            }
        } else {
            errorMessage(etUserSignIn)
        }
    }

    private fun errorMessage(campo: EditText) {
        campo.error = "Preencha este campo"
        campo.requestFocus()
    }


}