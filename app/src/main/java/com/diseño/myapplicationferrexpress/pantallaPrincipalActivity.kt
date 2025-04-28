package com.diseño.myapplicationferrexpress

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diseño.myapplicationferrexpress.adapter.ProductoAdapter
import com.example.myapplicationferrexpress.R

class pantallaPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_principal)

        // Ajustar márgenes para el sistema
        setupWindowInsets()

        // Configuración de botones
        val botonMenu = findViewById<ImageView>(R.id.btnmenu)
        botonMenu.setOnClickListener { showPopupMenu(botonMenu) }

        val botonCarrito = findViewById<ImageView>(R.id.botonCarrito)
        botonCarrito.setOnClickListener { navegacionCarrito() }

        val botonLogin = findViewById<Button>(R.id.botonLogin)
        botonLogin.setOnClickListener { navegacionLogin() }

        // Lista de productos y promociones
        val productos = listOf(
            Producto(1, "Taladro Inalámbrico", 254.915, "Taladro Bosch profesional", R.drawable.taladro, true),
            Producto(2, "Pulidora Skil", 292.492, "Pulidora angular 4 1/2 pulgadas", R.drawable.pulidora, true),
            Producto(3, "Maletín de Herramientas", 119.555, "Kit de herramientas 46 piezas", R.drawable.maletin, true),
            Producto(4, "Kit Atornillador", 1199900.0, "Atornillador eléctrico con accesorios", R.drawable.percutor, true)
        )

        val promociones = productos.filter { it.esPromocion } // Filtrar las promociones

        // Configuración del RecyclerView de productos
        val recyclerViewProductos = findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerViewProductos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewProductos.adapter = ProductoAdapter(productos, R.layout.activity_productos) { producto ->
            // Abrir diferentes pantallas según el ID del producto
            when (producto.id) {
                1 -> startActivity(Intent(this, pantallaProductosActivity::class.java))
                2 -> startActivity(Intent(this, pantallaProductos3Activity::class.java))
                3 -> startActivity(Intent(this, pantallaProductos2Activity::class.java))
                4 -> startActivity(Intent(this, pantallaProductos4Activity::class.java))
                else -> Toast.makeText(this, "Pantalla no disponible para este producto.", Toast.LENGTH_SHORT).show()
            }
        }

        // Configuración del RecyclerView de promociones
        val recyclerViewPromociones = findViewById<RecyclerView>(R.id.recyclerViewPromociones)
        recyclerViewPromociones.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPromociones.adapter = ProductoAdapter(promociones, R.layout.item_promocion) { promocion ->
            // Abrir diferentes pantallas según el ID de la promoción
            when (promocion.id) {
                1 -> startActivity(Intent(this, pantallaProductosActivity::class.java))
                2 -> startActivity(Intent(this, pantallaProductos3Activity::class.java))
                3 -> startActivity(Intent(this, pantallaProductos2Activity::class.java))
                4 -> startActivity(Intent(this, pantallaProductos4Activity::class.java))
                else -> Toast.makeText(this, "Pantalla no disponible para esta promoción.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Manejo de márgenes del sistema (barra de estado)
    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Mostrar menú desplegable
    private fun showPopupMenu(botonMenu: ImageView) {
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = layoutInflater.inflate(R.layout.popup_menu_layout, null)

        val popupWindow = PopupWindow(
            popupView,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        popupWindow.setBackgroundDrawable(resources.getDrawable(R.drawable.bordegeneral, null))
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true
        popupWindow.showAsDropDown(botonMenu)

        setupPopupMenuOptions(popupView, popupWindow)
    }

    // Opciones del menú desplegable
    private fun setupPopupMenuOptions(popupView: View, popupWindow: PopupWindow) {
        popupView.findViewById<View>(R.id.op1).setOnClickListener {
            Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
            startActivity(Intent(this, pantallaPrincipalActivity::class.java))
        }

        popupView.findViewById<View>(R.id.op2).setOnClickListener {
            Toast.makeText(this, "Promociones", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
        }

        popupView.findViewById<View>(R.id.op3).setOnClickListener {
            Toast.makeText(this, "Contacto", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
        }

        popupView.findViewById<View>(R.id.op4).setOnClickListener {
            Toast.makeText(this, "Nosotros", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
        }
    }

    // Navegaciones simples
    private fun navegacionCarrito() {
        val intent = Intent(this, CarritoDeComprasActivity::class.java)
        startActivity(intent)
    }

    private fun navegacionLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}