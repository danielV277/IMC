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
    lateinit var encryptionUtil:EncryptionUtil

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        coneccionDB = ConeccionDB(this)



        //val credenciales = hashMapOf(
        //    "admin" to "1234"
        //)
        //val secretKey = encryptionUtil.generateKey()
        //coneccionDB.addUsuario("admin",encryptionUtil.encrypt("1234",secretKey))

        coneccionDB.addUsuario("admin", "1234")

        val teUsuario = findViewById<EditText>(R.id.teUsuario)
        val teContra = findViewById<EditText>(R.id.teContra)

        val btEntrar = findViewById<Button>(R.id.btEntrar)
        val btRegistrase = findViewById<Button>(R.id.btRegistrase)

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

                    if(credenciales[usuario] == contra){
                        //credenciales[usuario] == EncryptionUtil.decrypt(contra,secretKey)
                        val intent = Intent(this,MainActivity::class.java)
                        intent.putExtra("usuario",usuario)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this,getString(R.string.mnCon),Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this,getString(R.string.vlCampos),Toast.LENGTH_LONG).show()
            }

            btRegistrase.setOnClickListener{
                val intentReg = Intent(this,Registrase::class.java)
                startActivity(intentReg)
            }




            /*if(usuario.isNotEmpty() && contra.isNotEmpty()){
                if(credenciales[usuario] == contra){
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,getString(R.string.mnCon),Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,getString(R.string.vlCampos),Toast.LENGTH_LONG).show()
            }*/

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}