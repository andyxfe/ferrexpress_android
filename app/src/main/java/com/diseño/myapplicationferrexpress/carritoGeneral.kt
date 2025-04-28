package com.diseño.myapplicationferrexpress

object Carrito {
    // Lista mutable para almacenar los productos seleccionados
    val productosSeleccionados = mutableListOf<Producto>()

    // Variable para almacenar el total del carrito
    var total: Double = 0.0
        private set // El setter es privado para proteger la integridad del total

    // Método para agregar productos al carrito
    fun agregarProducto(producto: Producto) {
        // Si el producto ya existe, incrementa su cantidad
        val productoExistente = productosSeleccionados.find { it.id == producto.id }
        if (productoExistente != null) {
            productoExistente.cantidad += producto.cantidad
        } else {
            productosSeleccionados.add(producto)
        }
        actualizarTotal()
    }

    // Método para eliminar productos del carrito
    fun eliminarProducto(producto: Producto) {
        productosSeleccionados.removeIf { it.id == producto.id }
        actualizarTotal()
    }

    // Método para vaciar completamente el carrito
    fun vaciarCarrito() {
        productosSeleccionados.clear()
        total = 0.0
    }

    // Método para calcular el total del carrito
    fun actualizarTotal() {
        total = productosSeleccionados.sumOf { it.precio * it.cantidad }
    }
}


