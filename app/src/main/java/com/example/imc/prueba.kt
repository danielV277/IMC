package com.example.imc

import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import android.content.Context


@RequiresApi(Build.VERSION_CODES.O)
lateinit var coneccionDB: ConeccionDB

fun main() {
    /*val secretKey = EncryptionUtil.generateKey();
    println(secretKey.toString()+"   vieja")

    val skBinary:ByteArray = secretKey.encoded

    val skRe:SecretKey = skBinary.let { SecretKeySpec(skBinary,"AES") }

    println(skRe.toString()+"   Regreso")*/


    /*var ecrip = EncryptionUtil.encrypt("1234",secretKey).toString()
    println(ecrip)

    var desc = EncryptionUtil.decrypt(ecrip,secretKey)
    println(desc)

   *val encri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


    } else {
        println("VERSION.SDK_INT < O")
    }*/


}