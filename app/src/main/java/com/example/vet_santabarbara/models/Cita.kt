package com.example.vet_santabarbara.models

data class Cita(
    val id_cita: Int? = null,
    val fecha: String,
    val fecha_cita: String?=null,
    val hora_cita: String?=null,
    val hora: String,
    val id_paciente: Int,
    val id_doctor: Int,
    val paciente: String?=null,
    val propietario: String?=null,
    val doctor: String?=null,
    val raza: String?=null,
    val telefono: String?=null,
    val color: String?=null,
    val especie: String?=null,
    val peso: Int?=null
)
