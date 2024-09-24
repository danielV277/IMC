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
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class Registrase : AppCompatActivity() {

    lateinit var coneccionDB: ConeccionDB

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

            //val secretKeyByte: ByteArray? = intent.getByteArrayExtra("secretKey")
            //val secretKey: SecretKey? = secretKeyByte?.let { SecretKeySpec(it,"AES")}
            //val contraseñaEncriptada = secretKey?.let { it1 -> EncryptionUtil.encrypt(password, it1) }


            if (username.isNotEmpty() && password.isNotEmpty()) {


                coneccionDB.addUsuario(username, EncryptionUtil.encrypt(password,EncryptionUtil.secretKey))
                CustomToast.show(this, "Usuario: $username\n se registro con exito",R.drawable.corazon)
                //Toast.makeText(this, "Usuario: $username\n se registro con exito", Toast.LENGTH_LONG).show()
                val intLogin = Intent(this,Login::class.java)
                startActivity(intLogin)
                finish()
            } else {
                // Si los campos están vacíos, mostrar un mensaje de error
                CustomToast.show(this, "Por favor, ingrese un usuario y una contraseña.",R.drawable.alerta)
                //Toast.makeText(this, "Por favor, ingrese un usuario y una contraseña.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}