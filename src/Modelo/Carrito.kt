package Modelo

class Carrito {
    private val items = mutableListOf<CarritoItem>()

    fun agregarProducto(producto: Producto, cantidad: Int) {
        if (producto.cantidadDisponible >= cantidad) {
            val carritoItem = items.find { it.producto.id == producto.id }
            if (carritoItem != null) {
                carritoItem.cantidad += cantidad
            } else {
                items.add(CarritoItem(producto, cantidad))
            }
            producto.cantidadDisponible -= cantidad
            println("Producto agregado al carrito: ${producto.nombre}, Cantidad: $cantidad")
        } else {
            throw IllegalArgumentException("Cantidad solicitada no disponible para el producto: ${producto.nombre}")
        }
    }

    fun eliminarProductoPorId(id: Int) {
        val item = items.find { it.producto.id == id }
        if (item != null) {
            items.remove(item)
            item.producto.cantidadDisponible += item.cantidad
            println("Producto eliminado del carrito: ${item.producto.nombre}")
        } else {
            throw IllegalArgumentException("Producto no encontrado en el carrito con ID: $id")
        }
    }

    fun mostrarCarrito(): List<CarritoItem> {
        return items
    }

    fun generarFactura(): Factura {
        val subtotal = items.sumOf { it.calcularPrecioTotal() }
        val porcentajeImpuesto = 0.13
        val impuestos = subtotal * porcentajeImpuesto
        val totalGeneral = subtotal + impuestos
        val fecha = java.time.LocalDate.now().toString()
        return Factura(
            items = items,
            subtotal = subtotal,
            porcentajeImpuesto = porcentajeImpuesto,
            impuestos = impuestos,
            total = totalGeneral,
            fecha = fecha
        )
    }

    fun limpiarCarrito() {
        items.clear()
        //println("El carrito ha sido limpiado.")
    }
}
