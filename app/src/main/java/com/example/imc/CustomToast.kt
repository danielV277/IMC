package com.example.imc
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class CustomToast() {
    companion object {
        fun show(context: Context, message: String, iconResId: Int, duration: Int = Toast.LENGTH_SHORT) {
            // Inflar el layout personalizado
            val layoutInflater = LayoutInflater.from(context)
            val view: View = layoutInflater.inflate(R.layout.custom_toast, null)

            // Configurar el texto del Toast
            val toastText: TextView = view.findViewById(R.id.toast_text)
            toastText.text = message

            // Configurar el icono del Toast
            val toastIcon: ImageView = view.findViewById(R.id.toast_icon)
            toastIcon.setImageResource(iconResId)

            // Crear y mostrar el Toast
            val toast = Toast(context)
            toast.duration = duration
            toast.view = view
            toast.show()
        }
    }

}