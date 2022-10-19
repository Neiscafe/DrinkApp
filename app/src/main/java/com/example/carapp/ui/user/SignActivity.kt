package com.example.carapp.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.carapp.R

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val btSignIn = findViewById<Button>(R.id.btSignIn)

        btSignIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}