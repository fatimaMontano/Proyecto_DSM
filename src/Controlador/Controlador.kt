package Controlador

import Modelo.Carrito
import Modelo.Producto
import Vista.Vista
import Modelo.obtenerProductos

object Controlador {
    private val carrito = Carrito()
    private val productos = obtenerProductos()

    fun iniciar() {
        mostrarMenuPrincipal()
    }

    private fun mostrarMenuPrincipal() {
        while (true) {
            Vista.mostrarMensaje(
                """
            =========================================
                 🌸 Bienvenid@ a Aura Skin Co. 🌸
            =========================================
            ✨ 1. Mostrar Productos
            ✨ 2. Agregar Producto al Carrito
            ✨ 3. Eliminar Producto al Carrito
            ✨ 4. Ver Carrito
            ❌ 5. Salir
            =========================================
            Por favor, selecciona una opción:
            """.trimIndent()
            )
            val opcion = readLine()
            when (opcion) {
                "1" -> mostrarProductos()
                "2" -> agregarProducto()
                "3" -> eliminarProducto()
                "4" -> verCarrito()
                "5" -> {
                    Vista.mostrarMensaje("Saliendo de la aplicación...")
                    System.exit(0)
                }
                else -> Vista.mostrarMensaje("Opción no válida. Intente de nuevo.")
            }
        }
    }

    fun mostrarProductos() {
        Vista.mostrarMensaje("══════════ ✨ Productos disponibles ✨ ════════════")
        Vista.mostrarMensaje("ID   | Producto            | Precio   | Stock ")
        Vista.mostrarMensaje("═══════════════════════════════════════════════════")

        for (producto in productos) {
            Vista.mostrarMensaje(
                String.format(
                    "%-5s| %-20s| $%-8s| %-5s",
                    producto.id, producto.nombre, producto.precio, producto.cantidadDisponible
                )
            )
        }
        Vista.mostrarMensaje("══════════════════════════════════════════════════")
        Vista.mostrarMensaje("\uD83D\uDD19 Presione 'Enter' para regresar al menú principal")
        readLine()
    }

    fun agregarProducto() {
        while (true) {
            // Pedir al usuario el ID del producto y la cantidad
            Vista.mostrarMensaje("Ingrese el ID del producto que desea agregar:")
            val id = readLine()?.toIntOrNull()
            Vista.mostrarMensaje("Ingrese la cantidad que desea agregar:")
            val cantidad = readLine()?.toIntOrNull()

            // Verificar si las entradas son válidas
            if (id != null && cantidad != null) {
                val producto = productos.find { it.id == id }
                if (producto != null) {
                    try {
                        carrito.agregarProducto(producto, cantidad)
                        Vista.mostrarMensaje("Producto agregado correctamente.")
                    } catch (e: IllegalArgumentException) {
                        Vista.mostrarMensaje("Error: ${e.message}")
                    }
                } else {
                    Vista.mostrarMensaje("Producto no encontrado.")
                }
            } else {
                Vista.mostrarMensaje("Entrada no válida.")
            }

            // Ofrecer opciones después de agregar un producto
            Vista.mostrarMensaje("Seleccione una opción:")
            Vista.mostrarMensaje("1. Agregar otro producto")
            Vista.mostrarMensaje("2. Regresar al menú principal")

            // Leer la opción del usuario
            when (readLine()) {
                "1" -> continue  // Volver a agregar otro producto
                "2" -> {
                    Vista.mostrarMensaje("Regresando al menú principal...\n")
                    return  // Salir del bucle y volver al menú principal
                }
                else -> Vista.mostrarMensaje("Opción no válida. Regresando al menú principal.")
            }
            return
        }
    }

    fun eliminarProducto(){
        Vista.mostrarMensaje("Ingresa el número del producto que deseas eliminar del carrito: ")
        val id = readLine()?.toIntOrNull()
        if (id != null) {
            val producto = productos.find { it.id == id }
            if (producto != null) {
                try {
                    carrito.eliminarProductoPorId(producto.id)
                } catch (e: IllegalArgumentException) {
                    Vista.mostrarMensaje("Error: ${e.message}")
                }
            } else {
                Vista.mostrarMensaje("Producto no encontrado.")
            }
        } else {
            Vista.mostrarMensaje("Entrada no válida.")
        }
        Vista.mostrarMensaje("Presione 'Enter' para regresar al menú principal")
        readLine()

    }

    fun verCarrito() {
        val items = carrito.mostrarCarrito()
        if (items.isEmpty()) {
            Vista.mostrarMensaje("El carrito está vacío.")
        } else {
            Vista.mostrarProductos(items)
        }
        while (true) {
            Vista.mostrarMensaje("Seleccione una opción:\n1. Seguir comprando\n2. Confirmar compra")
            when (readLine()) {
                "1" -> {
                    return
                }
                "2" -> {
                    val factura = carrito.generarFactura()
                    Vista.mostrarFactura(factura)
                    carrito.limpiarCarrito()
                    return
                }
                else -> {
                    // Manejar opción inválida
                    Vista.mostrarMensaje("Opción no válida. Por favor, seleccione '1' para seguir comprando o '2' para confirmar compra.")
                }
            }
        }
    }
}


