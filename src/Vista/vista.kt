package Vista

import Modelo.CarritoItem
import Modelo.Carrito

object Vista {
    fun mostrarProductos(productos: List<CarritoItem>) {
        var totalGeneral = 0.0
        println("════════════════ 🛒 Productos en el Carrito 🛒 ═══════════════════")
        println("Producto              | Cantidad | Precio Unitario | Precio Total")
        println("══════════════════════════════════════════════════════════════════")

        for (item in productos) {
            val precioTotalProducto = item.calcularPrecioTotal()
            totalGeneral += precioTotalProducto // Sumar al total general
            println(
                String.format(
                    "%-22s| %-8d| $%-13.2f| $%-10.2f",
                    item.producto.nombre, item.cantidad, item.producto.precio, precioTotalProducto
                )
            )
        }

        println("══════════════════════════════════════════════════════════════════")
        println(String.format("Total General del Carrito: $%.2f", totalGeneral))
        println("══════════════════════════════════════════════════════════════════")
    }

    fun mostrarFactura(factura: Modelo.Factura) {
        println("═════════════════════════  FACTURA  ════════════════════════════")
        println("Fecha de la Factura: ${factura.fecha}")
        println("════════════════════════════════════════════════════════════════")
        println("Producto              | Cantidad | Precio Unitario | Precio Total")
        println("════════════════════════════════════════════════════════════════")

        for (item in factura.items) {
            println(
                String.format(
                    "%-22s| %-8d| $%-13.2f| $%-10.2f",
                    item.producto.nombre, item.cantidad, item.producto.precio, item.calcularPrecioTotal()
                )
            )
        }

        println("════════════════════════════════════════════════════════════════")
        println(String.format("Subtotal:              $%.2f", factura.subtotal))
        println(String.format("Impuestos (%.2f%%):     $%.2f", factura.porcentajeImpuesto * 100, factura.impuestos))
        println("════════════════════════════════════════════════════════════════")
        println(String.format("Total General:         $%.2f", factura.total))
        println("════════════════════════════════════════════════════════════════")
        println("¡Gracias por su compra en Aura Skin Co.!")

        while (true) {
            println("\nSeleccione una opción:")
            println("1. Realizar nueva compra")
            println("2. Salir de la aplicación")

            // Capturar la entrada del usuario
            when (readLine()) {
                "1" -> {
                    // Regresar al menú principal
                    println("Regresando al menú principal...\n")
                    return  // Volverá al menú principal
                }
                "2" -> {
                    // Salir de la aplicación
                    println("Gracias por su compra. ¡Hasta pronto!")
                    System.exit(0)  // Termina la ejecución de la aplicación
                }
                else -> {
                    // Opción no válida
                    println("Opción no válida. Por favor, intente nuevamente.")
                }
            }
        }
    }

    fun mostrarMensaje(mensaje: String) {
        println(mensaje)
    }
}
