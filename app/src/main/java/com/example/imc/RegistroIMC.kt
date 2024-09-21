package com.example.imc

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
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
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val registrosL = obtenerRegistros()
        val adapter = RegistroAdapter(registrosL)
        recyclerView.adapter = adapter


    }

    fun obtenerRegistros(): MutableList<Registro> {
        val registros = mutableListOf<Registro>()
        val p0: SQLiteDatabase = coneccionDB.readableDatabase
        val cursor = p0.rawQuery("SELECT usuario,peso,altura,fecha FROM registros", null)

        if (cursor.moveToFirst()) {
            do {
                val usuario = cursor.getString(0)
                val peso = cursor.getString(1)
                val altura = cursor.getString(2)
                val fecha = cursor.getString(3)
                registros.add(Registro(usuario,peso,altura,fecha))
            } while (cursor.moveToNext())
            cursor.close()
        }
        return registros
    }
}