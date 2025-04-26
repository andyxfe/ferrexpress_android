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
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplicationferrexpress.R

class pantallaPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activar la funcionalidad Edge-to-Edge
        setContentView(R.layout.activity_pantalla_principal)

        // Manejo de márgenes de la pantalla para evitar solapamientos con la barra del sistema
        setupWindowInsets()

        // Configuración del botón del menú
        val botonMenu = findViewById<ImageView>(R.id.btnmenu)
        botonMenu.setOnClickListener {
            showPopupMenu(botonMenu)
        }
        val botonCarrito = findViewById<ImageView>(R.id.botonCarrito)
        botonCarrito.setOnClickListener { navegacionCarrito() }

        val botonLogin = findViewById<Button>(R.id.botonLogin)
        botonLogin.setOnClickListener { navegacionLogin() }

        val botonProductos = findViewById<CardView>(R.id.targetasProductos)
        botonProductos.setOnClickListener { navegacionProductos() }

        val botonProductos2 = findViewById<CardView>(R.id.targetasProductos2)
        botonProductos2.setOnClickListener { navegacionProductos2() }

        val botonProductos3 = findViewById<CardView>(R.id.targetasProductos3)
        botonProductos3.setOnClickListener { navegacionProductos3() }

        val botonProductos4 = findViewById<CardView>(R.id.targetasProductos4)
        botonProductos4.setOnClickListener { navegacionProductos4() }

        val botonPromocion1 = findViewById<CardView>(R.id.promocion1)
        botonPromocion1.setOnClickListener { navegacionPromocion1() }

        val botonPromocion2 = findViewById<CardView>(R.id.promocion2)
        botonPromocion2.setOnClickListener { navegacionPromocion2() }

        val botonPromocion3 = findViewById<CardView>(R.id.promocion3)
        botonPromocion3.setOnClickListener { navegacionPromocion3() }

        val botonPromocion4 = findViewById<CardView>(R.id.promocion4)
        botonPromocion4.setOnClickListener { navegacionPromocion4() }
    }

    /**
     * Configura el manejo de los márgenes de la pantalla para que se ajuste a la visibilidad de las barras del sistema.
     */
    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * Muestra el PopupMenu cuando el usuario hace clic en el botón del menú.
     */
    private fun showPopupMenu(botonMenu: ImageView) {
        // Inflar el layout del PopupWindow
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = layoutInflater.inflate(R.layout.popup_menu_layout, null)

        // Crear el PopupWindow
        val popupWindow = PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)

        // Cambiar el fondo del PopupWindow
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.parseColor("#0D0D10"))) // Fondo personalizado
        popupWindow.isOutsideTouchable = true // Permitir cierre al tocar fuera
        popupWindow.isFocusable = true // Asegurar que el PopupWindow reciba eventos

        // Mostrar el PopupWindow debajo del botón
        popupWindow.showAsDropDown(botonMenu)

        // Manejar las opciones del PopupWindow
        setupPopupMenuOptions(popupView, popupWindow)
    }

    /**
     * Configura los eventos de clic para cada opción del PopupWindow.
     */
    private fun setupPopupMenuOptions(popupView: View, popupWindow: PopupWindow) {
        // Manejo de la opción 1
        popupView.findViewById<View>(R.id.op1).setOnClickListener {
            Toast.makeText(this,"inicio", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
            val intent = Intent(this, pantallaPrincipalActivity::class.java)
            startActivity(intent)
        // Cerrar el PopupWindow
        }

        // Manejo de la opción 2
        popupView.findViewById<View>(R.id.op2).setOnClickListener {
            Toast.makeText(this, "Opción Promociones seleccionada", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss() // Cerrar el PopupWindow
        }

        // Manejo de la opción 3
        popupView.findViewById<View>(R.id.op3).setOnClickListener {
            Toast.makeText(this, "Opción Contacto seleccionada", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss() // Cerrar el PopupWindow
        }

        // Manejo de la opción 4
        popupView.findViewById<View>(R.id.op4).setOnClickListener {
            Toast.makeText(this, "Opción Nosotros seleccionada", Toast.LENGTH_SHORT).show()
            popupWindow.dismiss() // Cerrar el PopupWindow
        }


    }

    private fun navegacionCarrito(){
        val intent = Intent(this, CarritoDeComprasActivity::class.java)
        startActivity(intent)
    }
    private fun navegacionLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun navegacionProductos(){
        val intent = Intent(this, pantallaProductosActivity::class.java)
        startActivity(intent)
    }
        private fun navegacionProductos2(){
            val intent = Intent(this, pantallaProductos2Activity::class.java)
            startActivity(intent)
        }

    private fun navegacionProductos3(){
        val intent = Intent(this, pantallaProductos3Activity::class.java)
        startActivity(intent)
    }

    private fun navegacionProductos4(){
        val intent = Intent(this, pantallaProductos4Activity::class.java)
        startActivity(intent)
    }

    private fun navegacionPromocion1(){
        val intent = Intent(this, pantallaProductosActivity::class.java)
        startActivity(intent)
    }
    private fun navegacionPromocion2(){
        val intent = Intent(this, pantallaProductos2Activity::class.java)
        startActivity(intent)
    }
    private fun navegacionPromocion3(){
        val intent = Intent(this, pantallaProductos3Activity::class.java)
        startActivity(intent)
    }

    private fun navegacionPromocion4(){
        val intent = Intent(this, pantallaProductos4Activity::class.java)
        startActivity(intent)
    }


}
