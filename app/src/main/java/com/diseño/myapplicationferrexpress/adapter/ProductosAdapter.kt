package com.diseño.myapplicationferrexpress.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diseño.myapplicationferrexpress.Producto
import com.example.myapplicationferrexpress.R

class ProductoAdapter(private val productos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textNombre)
        val precio: TextView = itemView.findViewById(R.id.textPrecio)
        val descripcion: TextView = itemView.findViewById(R.id.textDescripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_pantalla_principal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productos= productos[position]
        holder.nombre.text = productos.nombre
        holder.precio.text = "Precio: $${productos.precio}"
        holder.descripcion.text = productos.descripcion
    }

    override fun getItemCount(): Int = productos.size

}