package Vista

import Modelo.CarritoItem

object Vista {
    fun mostrarProductos(productos: List<CarritoItem>) {
        println("Lista de productos en el carrito:")
        for (item in productos) {
            println("${item.producto.nombre} - Cantidad: ${item.cantidad} - Precio Total: ${item.calcularPrecioTotal()}")
        }
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
