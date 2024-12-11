package com.example.vet_santabarbara.models

data class Paciente(
    val id_paciente: Int? = null,
    val color: String,
    val correo: String,
    val deshabilitado: Int,
    val fecha_nac_mascota: String,
    val domicilio: String,
    val especie: String,
    val nombre_completo: String,
    val nombre_completo_propietario: String,
    val peso: Int,
    val raza: String,
    val telefono: String,
    val contrasenia: String?="cambiame"
)
