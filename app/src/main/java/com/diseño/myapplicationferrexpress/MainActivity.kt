package com.diseño.myapplicationferrexpress

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diseño.myapplicationferrexpress.adapter.ProductoAdapter
import com.example.myapplicationferrexpress.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        // Inicialización de la base de datos
        val dbHelper = DatabaseHelper(this)

        // Obtén los datos de productos y promociones
        val productos = dbHelper.obtenerListaProductos()
        val promociones = productos.filter { it.esPromocion } // Filtrar las promociones

        // Configuración del RecyclerView para productos
        val recyclerViewProductos = findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerViewProductos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewProductos.adapter = ProductoAdapter(productos, R.layout.activity_productos) { producto ->
            // Agregar el producto al carrito
            Carrito.agregarProducto(producto)
            Toast.makeText(this, "${producto.nombre} agregado al carrito", Toast.LENGTH_SHORT).show()

            // Abrir directamente la pantalla del carrito para mostrar el producto agregado
            val intent = Intent(this, CarritoDeComprasActivity::class.java)
            startActivity(intent)
        }

        // Configuración del RecyclerView para promociones
        val recyclerViewPromociones = findViewById<RecyclerView>(R.id.recyclerViewPromociones)
        recyclerViewPromociones.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPromociones.adapter = ProductoAdapter(promociones, R.layout.item_promocion) { promocion ->
            // Agregar la promoción al carrito
            Carrito.agregarProducto(promocion)
            Toast.makeText(this, "${promocion.nombre} agregado al carrito", Toast.LENGTH_SHORT).show()

            // Abrir directamente la pantalla del carrito para mostrar el producto agregado
            val intent = Intent(this, CarritoDeComprasActivity::class.java)
            startActivity(intent)
        }

        // Configuración del ícono de carrito para navegar a la pantalla del carrito
        val botonCarrito = findViewById<ImageView>(R.id.botonCarrito)
        botonCarrito.setOnClickListener {
            startActivity(Intent(this, CarritoDeComprasActivity::class.java))
        }

        // Manejo de datos enviados desde otra pantalla
        recibirProductoDesdeOtraPantalla()
    }

    private fun recibirProductoDesdeOtraPantalla() {
        val extras = intent.extras
        if (extras != null) {
            // Extraer los datos enviados por el Intent
            val productoId = extras.getInt("productoId")
            val productoNombre = extras.getString("productoNombre") ?: ""
            val productoPrecio = extras.getDouble("productoPrecio")
            val productoDescripcion = extras.getString("productoDescripcion") ?: ""
            val productoImagenResId = extras.getInt("productoImagenResId")
            val productoCantidad = extras.getInt("productoCantidad", 1)

            // Crear el objeto Producto y agregarlo al carrito
            val producto = Producto(
                id = productoId,
                nombre = productoNombre,
                precio = productoPrecio,
                descripcion = productoDescripcion,
                imagenResId = productoImagenResId,
                esPromocion = false, // Cambia según tu lógica
                cantidad = productoCantidad
            )
            Carrito.agregarProducto(producto)

            // Abrir directamente la pantalla del carrito
            val intent = Intent(this, CarritoDeComprasActivity::class.java)
            startActivity(intent)
        }
    }
}