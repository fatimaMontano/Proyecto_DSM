package Modelo

fun obtenerProductos(): List<Producto> {
    return listOf(
        Producto(id = 1, nombre = "Limpiador Facial", precio = 15.99, cantidadDisponible = 50),
        Producto(id = 2, nombre = "Tónico Hidratante", precio = 12.50, cantidadDisponible = 30),
        Producto(id = 3, nombre = "Crema Hidratante", precio = 20.00, cantidadDisponible = 25),
        Producto(id = 4, nombre = "Serum Revitalizante", precio = 35.00, cantidadDisponible = 15),
        Producto(id = 5, nombre = "Protector SPF 50", precio = 18.99, cantidadDisponible = 40),
        Producto(id = 6, nombre = "Mascarilla Facial", precio = 10.00, cantidadDisponible = 20),
        Producto(id = 7, nombre = "Exfoliante Suave", precio = 15.00, cantidadDisponible = 50),
        Producto(id = 8, nombre = "Contorno de Ojos", precio = 22.00, cantidadDisponible = 20),
        Producto(id = 9, nombre = "Serum Iluminador", precio = 28.00, cantidadDisponible = 30),
        Producto(id = 10, nombre = "Agua Micelar", precio = 12.00, cantidadDisponible = 60),
    )
}
