package com.diseño.myapplicationferrexpress

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val imagenResId: Int,
    val esPromocion: Boolean , // Campo para indicar si es una promoción
    var cantidad: Int =1
)