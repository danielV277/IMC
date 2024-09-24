package com.example.imc

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RegistroIMC : AppCompatActivity() {
    lateinit var coneccionDB: ConeccionDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_imc)
        coneccionDB = ConeccionDB(this)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val registrosL = obtenerRegistros()
        val adapter = RegistroAdapter(registrosL)
        recyclerView.adapter = adapter

        val btRegresar = findViewById<Button>(R.id.btRegresar)

        btRegresar.setOnClickListener{
            val intentMain = Intent(this, MainActivity::class.java)
            intentMain.putExtra("usuario",intent.getStringExtra("usuario"))
            startActivity(intentMain)
            finish()
        }

    }

    private fun obtenerRegistros(): MutableList<Registro> {
        val registros = mutableListOf<Registro>()
        registros.add(Registro("__Usuario__","__Peso__","__Altura__","__Fecha__"))
        val p0: SQLiteDatabase = coneccionDB.readableDatabase
        val cursor = p0.rawQuery("SELECT usuario,masa,altura,fecha FROM registros", null)

        if (cursor.moveToFirst()) {
            do {
                val usuario = cursor.getString(0)
                val masa = cursor.getString(1)
                val altura = cursor.getString(2)
                val fecha = cursor.getString(3)
                println(usuario+masa+altura+fecha)

                registros.add(Registro(usuario,masa,altura,fecha))
            } while (cursor.moveToNext())
            cursor.close()
        }
        return registros
    }
}