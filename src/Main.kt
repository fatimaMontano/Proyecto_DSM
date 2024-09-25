//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val productos = listOf(
        Producto("Producto A", 10.50, 10),
        Producto("Producto B", 15.75, 5),
        Producto("Producto C", 7.20, 8)
    )

    val carrito = Carrito()

    while (true) {
        println("\nMenú:")
        println("1. Mostrar productos")
        println("2. Agregar producto al carrito")
        println("3. Eliminar producto del carrito")
        println("4. Ver carrito")
        println("5. Confirmar compra")
        println("6. Salir")

        print("Selecciona una opción: ")
        val opcion = readLine()?.toIntOrNull()

        when (opcion) {
            1 -> {
                println("Productos disponibles:")
                productos.forEach { it.mostrarDetalle() }
            }
            2 -> {
                print("Ingresa el número del producto que deseas agregar: ")
                val indiceProducto = readLine()?.toIntOrNull()?.minus(1)
                if (indiceProducto != null && indiceProducto in productos.indices) {
                    val productoSeleccionado = productos[indiceProducto]
                    print("Cantidad a agregar: ")
                    val cantidad = readLine()?.toIntOrNull()
                    if (cantidad != null && cantidad > 0) {
                        carrito.agregarProducto(productoSeleccionado, cantidad)
                    } else {
                        println("Cantidad inválida.")
                    }
                } else {
                    println("Producto no encontrado.")
                }
            }
            3 -> {
                print("Ingresa el número del producto que deseas eliminar del carrito: ")
                val indiceProducto = readLine()?.toIntOrNull()?.minus(1)
                if (indiceProducto != null && indiceProducto in productos.indices) {
                    val productoSeleccionado = productos[indiceProducto]
                    carrito.eliminarProducto(productoSeleccionado)
                } else {
                    println("Producto no encontrado.")
                }
            }
            4 -> {
                carrito.mostrarCarrito()
            }
            5 -> {
                carrito.generarFactura()
                print("¿Deseas seguir comprando? (S/N): ")
                val respuesta = readLine()
                if (respuesta?.uppercase() == "S") {
                    carrito.limpiarCarrito()
                } else {
                    break
                }
            }
            6 -> {
                println("Gracias por usar el sistema de carrito de compras.")
                break
            }
            else -> {
                println("Opción inválida.")
            }
        }
    }
}