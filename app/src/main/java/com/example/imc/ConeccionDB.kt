package com.example.imc
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ConeccionDB (context:Context):SQLiteOpenHelper(context,"IMC.db",null,1 ){
    override fun onCreate(p0: SQLiteDatabase?) {
        val ordenUsu ="""
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT NOT NULL,
                contrasena TEXT NOT NULL
            )
        """
        val orderReg = """
        CREATE TABLE registros (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            usuario TEXT NOT NULL,
            masa TEXT NOT NULL,
            altura TEXT NOT NULL,
            fecha TEXT NOT NULL
        )
        """
        p0!!.execSQL(ordenUsu)
        p0!!.execSQL(orderReg)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS usuarios"
        p0!!.execSQL(ordenBorrado)
        onCreate(p0)
    }

    fun addUsuario(usu:String,cont:String){
        val datos = ContentValues()
        datos.put("usuario",usu)
        datos.put("contrasena",cont)

        val p0 = this.writableDatabase
        p0.insert("usuarios", null,datos)
        p0.close()
    }

    fun addRegistro(usu: String, peso:String,altura:String, fecha:String){
        val datos = ContentValues()
        datos.put("usuario",usu)
        datos.put("peso",peso)
        datos.put("altura",altura)
        datos.put("fecha",fecha)

        val p0 = this.writableDatabase
        p0.insert("registros", null,datos)
        p0.close()
    }

}