package com.example.vet_santabarbara.models

data class Doctor(val id_doctor: Int? = null, val nombre_completo: String, val especialidad: String, val fecha_nac: String,val domicilio: String, val telefono: String, val horario: String, val correo: String, val contrasenia: String?="cambiame")
