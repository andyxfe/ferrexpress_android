package com.diseño.myapplicationferrexpress

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplicationferrexpress.R
import java.util.Calendar

class registroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nombre = findViewById<EditText>(R.id.nombre)
        val errorNombre= findViewById<TextView>(R.id.errornombre)

        val apellido = findViewById<EditText>(R.id.apellido)
        val errorApellido = findViewById<TextView>(R.id.errorapellido)

        val cedula = findViewById<EditText>(R.id.cedula)
        val errorCedula = findViewById<TextView>(R.id.errorcedula)

        val fecha = findViewById<EditText>(R.id.fechanacimiento)
        val errorFecha = findViewById<TextView>(R.id.errorfechanacimiento)

        val correo = findViewById<EditText>(R.id.email)
        val errorCorreo = findViewById<TextView>(R.id.erroremail)

        val contrasena = findViewById<EditText>(R.id.contraseña)
        val errorContrasena = findViewById<TextView>(R.id.errorcontraseña)

        val boton = findViewById<Button>(R.id.botonregistrar)
        boton.setOnClickListener {
            val validNombre = validarCampo(nombre, errorNombre)
            val validApellido = validarCampo(apellido, errorApellido)
            val validCedula = validarCampo(cedula, errorCedula)
            val validFecha = validarCampo(fecha, errorFecha)
            val validCorreo = validarCampo(correo, errorCorreo)
            val validContrasena = validarCampo(contrasena, errorContrasena)

            if (validNombre && validApellido && validCedula && validFecha && validCorreo && validContrasena) {

                // puedo generar aqui el registro del usuario

                Toast.makeText(this, "Todo correcto", Toast.LENGTH_SHORT).show()
            }
        }


        val editTextFecha = findViewById<EditText>(R.id.fechanacimiento)
        editTextFecha.setOnClickListener {
            val calendario = Calendar.getInstance()
            val año = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val fechaFormateada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
                editTextFecha.setText(fechaFormateada)
            }, año, mes, dia)

            datePicker.show()
        }
    }
    private fun validarCampo(campo: EditText, errorView: TextView): Boolean {
        val texto = campo.text.toString().trim()
        return if (texto.isEmpty()) {
            errorView.visibility = View.VISIBLE
            false
        } else {
            errorView.visibility = View.GONE
            true
        }
    }

}