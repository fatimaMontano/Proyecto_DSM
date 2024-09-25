class Producto (val nombre: String, val precio: Double, var cantidadDisponible: Int) {

    fun mostrarDetalle() {
        println("$nombre - Precio: $$precio - Cantidad disponible: $cantidadDisponible")
    }
}