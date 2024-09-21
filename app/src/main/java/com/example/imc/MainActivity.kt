package com.example.imc

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    lateinit var coneccionDB: ConeccionDB
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        coneccionDB = ConeccionDB(this)

        val etAltura = findViewById<EditText>(R.id.etAltura)
        val etPeso = findViewById<EditText>(R.id.etPeso)
        val btCalcular = findViewById<Button>(R.id.btCalcular)
        val imgResultado = findViewById<ImageView>(R.id.imgResultado)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val usuario = intent.getStringExtra("usuario")

        val currentDateTime = LocalDateTime.now()
        // Definir el formato deseado
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        // Formatear la fecha y hora
        val formattedDateTime = currentDateTime.format(formatter)

        val btListar = findViewById<Button>(R.id.btListar)

        btCalcular.setOnClickListener{
            var alturaStr = etAltura.text.toString()
            var pesoStr = etPeso.text.toString()

            if(alturaStr.isNotEmpty() && pesoStr.isNotEmpty()){
                var altura:Float = alturaStr.toFloat()
                var peso:Float = pesoStr.toFloat()

                var imc = peso/(altura*altura)

                var result = when{
                    imc < 18.5 -> Pair("Bajo de peso",R.drawable.bajopeso)
                    imc >= 18.5 && imc < 25 -> Pair("Peso Saludable",R.drawable.normal)
                    imc >= 25 && imc < 30 -> Pair("Sobre Peso",R.drawable.sobrepeso)
                    imc >= 30 -> Pair("Obesidad",R.drawable.obesidad)
                    else -> Pair("",R.drawable.normal)
                }
                tvResultado.text = "IMC = ${imc}\nUsted esta en ${result.first}"
                imgResultado.setImageResource(result.second)
                if (usuario != null) {
                    coneccionDB.addRegistro(usuario, pesoStr,alturaStr,formattedDateTime)
                }
            }else{
                tvResultado.text = "Datos no validos"
            }
        }

        btListar.setOnClickListener{
            val intentRegistroiIMC = Intent(this,RegistroIMC::class.java)
            startActivity(intentRegistroiIMC)
            finish()
        }
    }
}