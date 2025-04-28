package com.diseño.myapplicationferrexpress.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diseño.myapplicationferrexpress.Carrito
import com.diseño.myapplicationferrexpress.Producto
import com.example.myapplicationferrexpress.R

class CarritoAdapter(
    private val productos: MutableList<Producto>, // Lista mutable de productos
    private val onUpdateTotal: (Double) -> Unit // Callback para actualizar el total
) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textNombre)
        val precio: TextView = itemView.findViewById(R.id.textPrecio)
        val cantidad: TextView = itemView.findViewById(R.id.product_quantity)
        val imagen: ImageView = itemView.findViewById(R.id.imageProducto)
        val botonIncrementar: Button = itemView.findViewById(R.id.increase_quantity)
        val botonReducir: Button = itemView.findViewById(R.id.decrease_quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_productos_agregados, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productoActual = productos[position]

        // Configurar los datos de la vista
        holder.nombre.text = productoActual.nombre
        holder.precio.text = "Precio: $${productoActual.precio * productoActual.cantidad}"
        holder.cantidad.text = productoActual.cantidad.toString()
        holder.imagen.setImageResource(productoActual.imagenResId)

        // Lógica para aumentar la cantidad
        holder.botonIncrementar.setOnClickListener {
            productoActual.cantidad += 1
            holder.cantidad.text = productoActual.cantidad.toString()
            holder.precio.text = "Precio: $${productoActual.precio * productoActual.cantidad}"
            actualizarTotal()
        }

        // Lógica para reducir la cantidad
        holder.botonReducir.setOnClickListener {
            val position = holder.adapterPosition // Obtén el índice actual del adaptador
            if (productoActual.cantidad > 1) {
                productoActual.cantidad -= 1
                holder.cantidad.text = productoActual.cantidad.toString()
                holder.precio.text = "Precio: $${productoActual.precio * productoActual.cantidad}"
                actualizarTotal()
            } else {
                // Eliminar producto de la lista si la cantidad es igual a 0
                if (position >= 0 && position < productos.size) {
                    productos.removeAt(position) // Elimina el producto de la lista
                    notifyItemRemoved(position) // Notifica al adaptador que se eliminó un elemento
                    notifyItemRangeChanged(position, productos.size) // Actualiza el rango de los elementos restantes
                    actualizarTotal()
                }
            }
        }
    }

    override fun getItemCount(): Int = productos.size

    // Método para actualizar el total
    private fun actualizarTotal() {
        Carrito.actualizarTotal() // Método dentro de Carrito para calcular el total
        onUpdateTotal(Carrito.total) // Callback para reflejar los cambios en la interfaz
    }

}