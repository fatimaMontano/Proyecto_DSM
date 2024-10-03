package Vista

import Modelo.CarritoItem

object Vista {
    fun mostrarProductos(productos: List<CarritoItem>) {
        println("═══════════ 🛒 Productos en el Carrito 🛒 ═══════════")
        println("Producto              | Cantidad | Precio Total ")
        println("═════════════════════════════════════════════════════")
        for (item in productos) {
            println(
                String.format(
                    "%-22s| %-8s| $%-12s",
                    item.producto.nombre, item.cantidad, item.calcularPrecioTotal()
                )
            )
        }
        println("═════════════════════════════════════════════════════")
    }

    fun mostrarFactura(factura: Modelo.Factura) {
        println("Factura - Fecha: ${factura.fecha}")
        for (item in factura.items) {
            println("${item.producto.nombre} - Cantidad: ${item.cantidad} - Precio Total: ${item.calcularPrecioTotal()}")
        }
        println("Total General: ${factura.total}")
    }

    fun mostrarMensaje(mensaje: String) {
        println(mensaje)
    }
}
