package com.example.carapp.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.carapp.MainActivity
import com.example.carapp.R
import com.example.carapp.database.UserDatabase
import com.example.carapp.database.dao.UserDao

class LoginActivity : AppCompatActivity() {

    private lateinit var dao: UserDao
    private lateinit var viewModel: SignActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_content)

        dao = UserDatabase.getInstance(this).getUserDao()

        val btLogin = findViewById<Button>(R.id.btLogin)
        val etCliqueAqui = findViewById<TextView>(R.id.tvCliqueAqui)
        val etUserLogin = findViewById<EditText>(R.id.etUserLogin)
        val etPasswordLogin = findViewById<EditText>(R.id.etPasswordLogin)

        btLogin.setOnClickListener {
            if (!etUserLogin.text.isNullOrEmpty()) {
                if (!etPasswordLogin.text.isNullOrEmpty()) {

                    viewModel =
                        SignActivityViewModel(
                            dao = dao,
                            userName = etUserLogin.text.toString(),
                            password = etPasswordLogin.text.toString()
                        )

                    viewModel.existsUser.observe(this, Observer {
                        if (it == true) {
                            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            Toast.makeText(this, "Usuário não existe!", Toast.LENGTH_SHORT).show()
                            etUserLogin.setText("")
                            etPasswordLogin.setText("")
                        }
                    })
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