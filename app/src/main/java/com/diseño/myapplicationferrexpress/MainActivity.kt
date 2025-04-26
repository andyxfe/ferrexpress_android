package com.diseño.myapplicationferrexpress

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diseño.myapplicationferrexpress.adapter.ProductoAdapter
import com.diseño.myapplicationferrexpress.ui.theme.MyApplicationferrexpressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationferrexpressTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        // Instancia de la base de datos
        val dbHelper = DatabaseHelper(this)

        // Insertar un producto
        dbHelper.insertarProducto(
            "WORKPRO",
            254.915,
            "Taladro Inalámbrico De Rotación 1/2 2P/G + 69 Accesorios"
        )

        // Obtener los productos de la base de datos como una lista
        val productos: List<Producto> = dbHelper.obtenerListaProductos()

        // Configurar el RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductoAdapter(productos)

        // Imprimir los productos en Logcat
        productos.forEach { producto ->
            Log.d(
                "DB",
                "ID: ${producto.id}, Nombre: ${producto.nombre}, Precio: ${producto.precio}, Descripción: ${producto.descripcion}"
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationferrexpressTheme {
        Greeting("Android")
    }
}