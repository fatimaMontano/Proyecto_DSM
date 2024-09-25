package Modelo

data class Factura(
    val items: List<CarritoItem>,
    val total: Double,
    val fecha: String
)
