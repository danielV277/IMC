package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val credenciales = hashMapOf(
            "admin" to "1234"
        )

        val teUsuario = findViewById<EditText>(R.id.teUsuario)
        val teContra = findViewById<EditText>(R.id.teContra)

        val btEntrar = findViewById<Button>(R.id.btEntrar)

        btEntrar.setOnClickListener{
            var usuario = teUsuario.text.toString().trim()
            var contra = teContra.text.toString().trim()


            if(usuario.isNotEmpty() && contra.isNotEmpty()){
                if(credenciales[usuario] == contra){
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,getString(R.string.mnCon),Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,getString(R.string.vlCampos),Toast.LENGTH_LONG).show()
            }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}