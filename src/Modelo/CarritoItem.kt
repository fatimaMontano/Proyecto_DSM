package Modelo

data class CarritoItem(val producto: Producto, var cantidad: Int) {
    fun calcularPrecioTotal(): Double {
        return producto.precio * cantidad
    }

    fun disminuirCantidad(cantidadARestar: Int) {
        if (cantidadARestar <= cantidad) {
            cantidad -= cantidadARestar
        } else {
            throw IllegalArgumentException("No se puede disminuir más de lo que hay en el carrito.")
        }
    }

    fun aplicarDescuento(descuentoPorcentaje: Double) {
        val descuento = producto.precio * (descuentoPorcentaje / 100)
        val precioConDescuento = producto.precio - descuento
        println("Precio después de aplicar el descuento: $precioConDescuento")
    }
}

