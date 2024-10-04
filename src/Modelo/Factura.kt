package Modelo

data class Factura(
    val items: List<CarritoItem>,      // Lista de productos en la factura
    val subtotal: Double,              // Suma del precio total de los productos sin impuestos
    val porcentajeImpuesto: Double,    // El porcentaje de impuestos (por ejemplo, 0.13 para el 13%)
    val impuestos: Double,             // Valor calculado de los impuestos
    val total: Double,                 // Total general incluyendo impuestos
    val fecha: String
)
