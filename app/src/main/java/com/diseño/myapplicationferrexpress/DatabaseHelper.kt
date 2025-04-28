package com.diseño.myapplicationferrexpress

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "productos.db"
        private const val DATABASE_VERSION = 2 // Incrementado para reflejar cambios en la tabla
        private const val TABLE_NAME = "productos"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_PRECIO = "precio"
        private const val COLUMN_DESCRIPCION = "descripcion"
        private const val COLUMN_IMAGEN = "imagen" // Nueva columna para las imágenes
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_PRECIO REAL NOT NULL,
                $COLUMN_DESCRIPCION TEXT NOT NULL,
                $COLUMN_IMAGEN INTEGER NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Elimina la tabla existente y la recrea con la nueva estructura
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertarProducto(nombre: String, precio: Double, descripcion: String, imagenResId: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, nombre)
            put(COLUMN_PRECIO, precio)
            put(COLUMN_DESCRIPCION, descripcion)
            put(COLUMN_IMAGEN, imagenResId) // Inserta la referencia del recurso de imagen
        }
        val result = db.insert(TABLE_NAME, null, values)
        if (result != -1L) {
            Log.d("DB", "Producto insertado correctamente: $nombre")
        } else {
            Log.d("DB", "Error al insertar el producto: $nombre")
        }
        db.close()
    }

    fun obtenerListaProductos(): List<Producto> {
        val productos = mutableListOf<Producto>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM productos", null)

        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow("id"))
                val nombre = it.getString(it.getColumnIndexOrThrow("nombre"))
                val precio = it.getDouble(it.getColumnIndexOrThrow("precio"))
                val descripcion = it.getString(it.getColumnIndexOrThrow("descripcion"))
                val imagenResId = it.getInt(it.getColumnIndexOrThrow("imagen"))
                val esPromocion = it.getInt(it.getColumnIndexOrThrow("esPromocion")) == 1

                // Construir objeto Producto con todos los valores necesarios
                productos.add(Producto(id, nombre, precio, descripcion, imagenResId, esPromocion))
            }
        }
        return productos
    }


}