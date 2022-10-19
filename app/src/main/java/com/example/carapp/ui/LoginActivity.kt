package com.example.carapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.carapp.MainActivity
import com.example.carapp.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_content)

        val btLogin = findViewById<Button>(R.id.btLogin)

        btLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }


}