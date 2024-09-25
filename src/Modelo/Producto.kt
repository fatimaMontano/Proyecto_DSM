package Modelo

data class Producto(
    val id: Int,
    val nombre: String,
    var precio: Double,
    var cantidadDisponible: Int
)
