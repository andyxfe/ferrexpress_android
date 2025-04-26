package com.diseño.myapplicationferrexpress

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "productos.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "productos"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_PRECIO = "precio"
        private const val COLUMN_DESCRIPCION = "descripcion"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT,
                $COLUMN_PRECIO REAL,
                $COLUMN_DESCRIPCION TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertarProducto(nombre: String,precio: Double,descripcion: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, nombre)
            put(COLUMN_PRECIO, precio)
            put(COLUMN_DESCRIPCION, descripcion)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun obtenerListaProductos(): List<Producto> {
        val productos = mutableListOf<Producto>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM productos", null)

        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(0)
                val nombre = it.getString(1)
                val precio = it.getDouble(2)
                val descripcion = it.getString(3)
                productos.add(Producto(id, nombre, precio, descripcion))
            }
        }
        return productos
    }
}