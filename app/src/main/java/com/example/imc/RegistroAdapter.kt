package com.example.imc
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RegistroAdapter(private val registros: MutableList<Registro>) : RecyclerView.Adapter<RegistroAdapter.RegistroViewHolder>() {

    class RegistroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsuario: TextView = itemView.findViewById(R.id.tvUsuario)
        val tvPeso: TextView = itemView.findViewById(R.id.tvPeso)
        val tvAltura: TextView = itemView.findViewById(R.id.tvAltura)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return RegistroViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegistroViewHolder, position: Int) {
        val registro = registros[position]
        holder.tvUsuario.text = registro.usuario
        holder.tvPeso.text = registro.peso
        holder.tvAltura.text = registro.altura
        holder.tvFecha.text = registro.fecha
    }

    override fun getItemCount(): Int {
        return registros.size
    }
}
