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
            Vista.mostrarMensaje("1. Mostrar Productos\n2. Agregar Producto\n3. Ver Carrito\n4. Salir")
            val opcion = readLine()
            when (opcion) {
                "1" -> mostrarProductos()
                "2" -> agregarProducto()
                "3" -> verCarrito()
                "4" -> {
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
        Vista.mostrarMensaje("Regresar al menú principal")
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
                    Vista.mostrarMensaje("Producto agregado al carrito: ${producto.nombre}, Cantidad: $cantidad")
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
    }

    fun verCarrito() {
        val items = carrito.mostrarCarrito()
        if (items.isEmpty()) {
            Vista.mostrarMensaje("El carrito está vacío.")
        } else {
            Vista.mostrarProductos(items)
        }
        Vista.mostrarMensaje("Regresar al menú principal\nConfirmar compra")
    }
}

