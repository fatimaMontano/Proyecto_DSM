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
                    return
                }
                else -> Vista.mostrarMensaje("Opción no válida. Intente de nuevo.")
            }
        }
    }

    fun mostrarProductos() {
        Vista.mostrarMensaje("Productos disponibles:")
        for (producto in productos) {
            Vista.mostrarMensaje("${producto.id}. ${producto.nombre} - Precio: $${producto.precio} - Cantidad Disponible: ${producto.cantidadDisponible}")
        }
        Vista.mostrarMensaje("Presione 'Enter' para regresar al menú principal")
        readLine()
    }

    fun agregarProducto() {
        Vista.mostrarMensaje("Ingrese el ID del producto que desea agregar:")
        val id = readLine()?.toIntOrNull()
        Vista.mostrarMensaje("Ingrese la cantidad que desea agregar:")
        val cantidad = readLine()?.toIntOrNull()

        if (id != null && cantidad != null) {
            val producto = productos.find { it.id == id }
            if (producto != null) {
                try {
                    carrito.agregarProducto(producto, cantidad)
                } catch (e: IllegalArgumentException) {
                    Vista.mostrarMensaje("Error: ${e.message}")
                }
            } else {
                Vista.mostrarMensaje("Producto no encontrado.")
            }
        } else {
            Vista.mostrarMensaje("Entrada no válida.")
        }
        Vista.mostrarMensaje("Regresar al menú principal o agregar otro producto.")
        readLine()
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
        Vista.mostrarMensaje("Regresar al menú principal\nConfirmar compra")
        readLine()
    }
}


