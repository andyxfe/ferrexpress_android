package com.diseño.myapplicationferrexpress.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.diseño.myapplicationferrexpress.Carrito
import com.diseño.myapplicationferrexpress.Producto
import com.example.myapplicationferrexpress.R

class ProductoAdapter(
    private val productos: List<Producto>,
    private val layoutResId: Int,
    private val onItemClick: (Producto) -> Unit // Listener para manejar clics
) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textNombre)
        val precio: TextView = itemView.findViewById(R.id.textPrecio)
        val descripcion: TextView = itemView.findViewById(R.id.textDescripcion)
        val imagen: ImageView = itemView.findViewById(R.id.imageProducto)
        val botonAgregar: Button? = itemView.findViewById(R.id.btnAgregarCarrito) // Botón opcional
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productoActual = productos[position]

        holder.nombre.text = productoActual.nombre
        holder.precio.text = "Precio: $${productoActual.precio}"
        holder.descripcion.text = productoActual.descripcion
        holder.imagen.setImageResource(productoActual.imagenResId)

        // Listener para manejar clics
        holder.itemView.setOnClickListener {
            onItemClick(productoActual) // Llama al listener con el producto actual
        }

        // Manejar el botón si existe
        holder.botonAgregar?.setOnClickListener {
            Carrito.agregarProducto(productoActual)
            Toast.makeText(
                holder.itemView.context,
                "${productoActual.nombre} agregado al carrito",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun getItemCount(): Int = productos.size
}


