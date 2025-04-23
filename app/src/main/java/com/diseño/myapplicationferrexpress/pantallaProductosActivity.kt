package com.diseño.myapplicationferrexpress

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
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
    }
    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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

}