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

    fun disminuirCantidad(id: Int, cantidadARestar: Int) {
        val item = items.find { it.producto.id == id }
        if (item != null) {
            item.disminuirCantidad(cantidadARestar)
            if (item.cantidad == 0) {
                items.remove(item)
                println("El producto ${item.producto.nombre} ha sido eliminado del carrito porque la cantidad es 0.")
            } else {
                println("Se ha disminuido la cantidad de ${item.producto.nombre}. Nueva cantidad: ${item.cantidad}")
            }
        } else {
            throw IllegalArgumentException("Producto no encontrado en el carrito con ID: $id")
        }
    }

    fun mostrarCarrito(): List<CarritoItem> {
        return items
    }

    fun generarFactura(): Factura {
        val totalGeneral = items.sumOf { it.calcularPrecioTotal() }
        val fecha = java.time.LocalDate.now().toString()
        return Factura(items, totalGeneral, fecha)
    }

    fun limpiarCarrito() {
        items.clear()
        println("El carrito ha sido limpiado.")
    }
}
