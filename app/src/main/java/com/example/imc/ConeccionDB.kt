package com.example.imc
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.SQLException

class ConeccionDB (context:Context):SQLiteOpenHelper(context,"IMC.db",null,1 ){
    override fun onCreate(p0: SQLiteDatabase?) {
        val ordenUsu ="""
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT NOT NULL,
                contrasena TEXT NOT NULL
            )
        """
        val ordenReg = """
        CREATE TABLE registros(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            usuario TEXT NOT NULL,
            masa TEXT NOT NULL,
            altura TEXT NOT NULL,
            fecha TEXT NOT NULL
        )
        """

        try {
            p0!!.execSQL(ordenReg)
            p0.execSQL(ordenUsu)
            println("Hola gato")
        } catch (e: SQLException) {
            Log.e("DB_ERROR", "Error creando tablas: ${e.message}")
        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS usuarios"
        val ordenBorradoReg = "DROP TABLE IF EXISTS registros"
        p0!!.execSQL(ordenBorrado)
        p0.execSQL(ordenBorradoReg)
        onCreate(p0)
    }

    fun addUsuario(usu:String,cont:String){
        val p1 = this.readableDatabase

        // Consulta para obtener información sobre las columnas de la tabla 'registros'
        val cursor = p1.rawQuery("PRAGMA table_info(registros)", null)

        if (cursor != null && cursor.moveToFirst()) {
            // El nombre de la columna está en el índice 1 (según la estructura de PRAGMA)
            do {
                val nombreColumna = cursor.getString(1) // Columna 1: nombre de la columna
                println("Columna: $nombreColumna")
            } while (cursor.moveToNext())
        } else {
            println("No se encontraron columnas o la tabla no existe.")
        }

        cursor.close()
        p1.close()

        val datos = ContentValues()
        datos.put("usuario",usu)
        datos.put("contrasena",cont)

        val p0 = this.writableDatabase
        p0.insert("usuarios", null,datos)
        p0.close()
    }

    fun addRegistro(usu: String, masa:String,altura:String, fecha:String){
        val p0 = this.writableDatabase
        // Comprobar si la tabla 'registros' existe
        val cursor = p0.rawQuery(
            "SELECT name FROM sqlite_master WHERE type='table' AND name='registros'",
            null
        )

        // Si la tabla no existe, crearla
        if (cursor.count == 0) {
            val crearTabla = """
            CREATE TABLE registros (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT NOT NULL,
                masa TEXT NOT NULL,
                altura TEXT NOT NULL,
                fecha TEXT NOT NULL
            )
        """
            p0.execSQL(crearTabla)
        }
        cursor.close()

        // Insertar los datos en la tabla 'registros'
        val datos = ContentValues().apply {
            put("usuario", usu)
            put("masa", masa)
            put("altura", altura)
            put("fecha", fecha)
        }

        p0.insert("registros", null, datos)
        p0.close()



        /*val datos = ContentValues()
        datos.put("usuario",usu)
        datos.put("masa",masa)
        datos.put("altura",altura)
        datos.put("fecha",fecha)

        val p0 = this.writableDatabase
        p0.insert("registros", null,datos)
        p0.close()*/
    }

}