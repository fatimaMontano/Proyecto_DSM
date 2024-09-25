class Carrito {
    private val productosEnCarrito = mutableListOf<Pair<Producto, Int>>()

    fun agregarProducto(producto: Producto, cantidad: Int) {
        if (producto.cantidadDisponible >= cantidad) {
            productosEnCarrito.add(Pair(producto, cantidad))
            producto.cantidadDisponible -= cantidad
            println("Producto agregado al carrito: ${producto.nombre}, Cantidad: $cantidad")
        } else {
            println("No hay suficiente stock de ${producto.nombre}.")
        }
    }

    fun eliminarProducto(producto: Producto) {
        val productoEncontrado = productosEnCarrito.find { it.first == producto }
        if (productoEncontrado != null) {
            productosEnCarrito.remove(productoEncontrado)
            producto.cantidadDisponible += productoEncontrado.second
            println("Producto eliminado del carrito: ${producto.nombre}")
        } else {
            println("El producto no está en el carrito.")
        }
    }

    fun mostrarCarrito() {
        if (productosEnCarrito.isEmpty()) {
            println("El carrito está vacío.")
        } else {
            println("Productos en el carrito:")
            productosEnCarrito.forEach { (producto, cantidad) ->
                println("${producto.nombre} - Cantidad: $cantidad - Precio unitario: $${producto.precio} - Total: $${producto.precio * cantidad}")
            }
            println("Total general: $${calcularTotal()}")
        }
    }

    fun calcularTotal(): Double {
        return productosEnCarrito.sumByDouble { (producto, cantidad) -> producto.precio * cantidad }
    }

    fun generarFactura() {
        println("Factura de compra:")
        mostrarCarrito()
        val total = calcularTotal()
        val impuestos = total * 0.13 // Impuesto del 13%
        val totalFinal = total + impuestos
        println("Impuestos: $$impuestos")
        println("Total con impuestos: $$totalFinal")
    }

    fun limpiarCarrito() {
        productosEnCarrito.clear()
    }
}