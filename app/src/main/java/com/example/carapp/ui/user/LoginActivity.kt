package com.example.carapp.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.carapp.MainActivity
import com.example.carapp.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_content)

        val btLogin = findViewById<Button>(R.id.btLogin)
        val etCliqueAqui = findViewById<TextView>(R.id.tvCliqueAqui)
        val etUserLogin = findViewById<EditText>(R.id.etUserLogin)
        val etPasswordLogin = findViewById<EditText>(R.id.etPasswordLogin)

        btLogin.setOnClickListener {
            if (!etUserLogin.text.isNullOrEmpty()) {
                if (!etPasswordLogin.text.isNullOrEmpty()) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    errorMessage(etPasswordLogin)
                }
            } else {
                errorMessage(etUserLogin)
            }
        }

        etCliqueAqui.setOnClickListener {
            startActivity(Intent(this, SignActivity::class.java))
        }
    }

    private fun errorMessage(campo: EditText) {
        campo.error = "Preencha este campo"
        campo.requestFocus()
    }

}