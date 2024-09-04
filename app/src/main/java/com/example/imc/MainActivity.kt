package com.example.imc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etAltura = findViewById<EditText>(R.id.etAltura)
        val etPeso = findViewById<EditText>(R.id.etPeso)
        val btCalcular = findViewById<Button>(R.id.btCalcular)
        val imgResultado = findViewById<ImageView>(R.id.imgResultado)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

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
            }else{
                tvResultado.text = "Datos no validos"
            }
        }
    }
}