package com.example.imc

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {

    lateinit var coneccionDB: ConeccionDB

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        coneccionDB = ConeccionDB(this)
        //val secretKey = EncryptionUtil.generateKey()


        coneccionDB.addUsuario("admin",EncryptionUtil.encrypt("1234",EncryptionUtil.secretKey))

        //coneccionDB.addUsuario("admin", "1234")

        val bun = Bundle()

        val teUsuario = findViewById<EditText>(R.id.teUsuario)
        val teContra = findViewById<EditText>(R.id.teContra)
        val btEntrar = findViewById<Button>(R.id.btEntrar)
        val btRegistrase = findViewById<Button>(R.id.btRegistrase)

        btRegistrase.setOnClickListener{
            val intent = Intent(this,Registrase::class.java)
            startActivity(intent)
        }

        btEntrar.setOnClickListener{
            var usuario = teUsuario.text.toString().trim()
            var contra = teContra.text.toString().trim()

            if(usuario.isNotEmpty() && contra.isNotEmpty()){

                val p0: SQLiteDatabase = coneccionDB.readableDatabase

                val cursor = p0.rawQuery("select usuario, contrasena from usuarios",null)
                if(cursor.moveToFirst()){
                    val credenciales = hashMapOf<String,String>()
                    do{
                        credenciales[cursor.getString(0).toString()] = cursor.getString(1).toString()
                    }while(cursor.moveToNext())

                    if(credenciales[usuario] == EncryptionUtil.encrypt(contra,EncryptionUtil.secretKey).toString()){

                        val intent = Intent(this,MainActivity::class.java)
                        intent.putExtra("usuario",usuario)
                        startActivity(intent)
                        finish()
                    }else{
                        CustomToast.show(this,getString(R.string.mnCon),R.drawable.alerta)
                        //Toast.makeText(this,getString(R.string.mnCon),Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                CustomToast.show(this,getString(R.string.vlCampos),R.drawable.alerta)
                //Toast.makeText(this,getString(R.string.vlCampos),Toast.LENGTH_LONG).show()
            }

        }

    }
}