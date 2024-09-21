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

class Registrase : AppCompatActivity() {

    lateinit var coneccionDB: ConeccionDB
    lateinit var encryptionUtil:EncryptionUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrase)

        coneccionDB = ConeccionDB(this)

        val usernameEditText = findViewById<EditText>(R.id.editTextUsername)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val registerButton = findViewById<Button>(R.id.buttonRegister)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Verificar que los campos no estén vacíos
            if (username.isNotEmpty() && password.isNotEmpty()) {

                coneccionDB.addUsuario(username,password)
                Toast.makeText(this, "Usuario: $username\n se registro con exito", Toast.LENGTH_LONG).show()
                val intLogin = Intent(this,Login::class.java)
                startActivity(intLogin)
                finish()
            } else {
                // Si los campos están vacíos, mostrar un mensaje de error
                Toast.makeText(this, "Por favor, ingrese un usuario y una contraseña.", Toast.LENGTH_SHORT).show()
            }
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}