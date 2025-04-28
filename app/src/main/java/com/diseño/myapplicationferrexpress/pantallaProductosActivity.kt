package com.diseño.myapplicationferrexpress

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import com.example.myapplicationferrexpress.R

class pantallaProductosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_productos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val botonMenu = findViewById<ImageView>(R.id.btnmenu)
        botonMenu.setOnClickListener {
            showPopupMenu(botonMenu)
        }
        val botonLogin = findViewById<Button>(R.id.botonLogin)
        botonLogin.setOnClickListener { navegacionLogin() }

        val botonAgregar = findViewById<Button>(R.id.botonañadircarrtio)
        botonAgregar.setOnClickListener {
            // Crear un Intent para enviar los datos del producto al carrito
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("productoId", 1) // ID del producto
                putExtra("productoNombre", "Taladro Inalámbrico")
                putExtra("productoPrecio", 254.492)
                putExtra("productoDescripcion", "Taladro Bosch profesional")
                putExtra("productoImagenResId", R.drawable.taladro)
                putExtra("productoCantidad", 1)
            }
            startActivity(intent) // Navegar de regreso al MainActivity o al lugar donde se maneje el carrito
            Toast.makeText(this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
        }


        val imagenPrincipal =findViewById<ImageView>(R.id.imagenPrincipal)
        val miniatura1 =findViewById<ImageView>(R.id.imagen1)
        val miniatura2 =findViewById<ImageView>(R.id.imagen2)
        val miniatura3 =findViewById<ImageView>(R.id.imagen3)
        val miniatura4 =findViewById<ImageView>(R.id.imagen4)

        asignarCambioImagen(miniatura1,imagenPrincipal,R.drawable.taladro)
        asignarCambioImagen(miniatura2,imagenPrincipal,R.drawable.taladro2)
        asignarCambioImagen(miniatura3,imagenPrincipal,R.drawable.taladro3)
        asignarCambioImagen(miniatura4,imagenPrincipal,R.drawable.taladro4)

    }
    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun asignarCambioImagen(miniatura: ImageView, imagenPrincipal: ImageView, imagenResId: Int) {
        miniatura.setOnClickListener {
            imagenPrincipal.setImageResource(imagenResId)
        }
    }

    private fun showPopupMenu(botonMenu: ImageView) {
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = layoutInflater.inflate(R.layout.popup_menu_layout, null)

        val popupWindow = PopupWindow(
            popupView,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.parseColor("#0D0D10")))
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        popupWindow.showAsDropDown(botonMenu)

        setupPopupMenuOptions(popupView, popupWindow)
    }

    private fun setupPopupMenuOptions(popupView: View, popupWindow: PopupWindow) {
        popupView.findViewById<View>(R.id.op1).setOnClickListener {
            Toast.makeText(this, "inicio", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
            val intent = Intent(this, pantallaPrincipalActivity::class.java)
            startActivity(intent)
        }

        popupView.findViewById<View>(R.id.op2).setOnClickListener {
            Toast.makeText(this, "Opción Promociones seleccionada", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
            val intent = Intent(this, pantallaPrincipalActivity::class.java)
            startActivity(intent)
        }

        popupView.findViewById<View>(R.id.op3).setOnClickListener {
            Toast.makeText(this, "Opción Contacto seleccionada", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
        }

        popupView.findViewById<View>(R.id.op4).setOnClickListener {
            Toast.makeText(this, "Opción Nosotros seleccionada", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
        }
    }
    private fun navegacionLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


}