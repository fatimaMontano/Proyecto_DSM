package com.example.vet_santabarbara.api

import com.example.vet_santabarbara.models.Cita
import com.example.vet_santabarbara.models.Doctor
import com.example.vet_santabarbara.models.Paciente
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("doctores")
    fun getDoctores(): Call<List<Doctor>>

    @GET("doctores/{doctorId}")
    fun getDoctorDetails(@Path("doctorId") doctorId: Int): Call<Doctor>

    @POST("doctores/modificar/{id}")
    fun modificarDoctor(@Path("id") doctorId: Int, @Body doctor: Doctor): Call<Doctor>
    @POST("doctores/nuevo")
    fun crearDoctor(@Body doctor: Doctor): Call<Doctor>

    @GET("pacientes")
    fun getPacientes(): Call<List<Paciente>>

    @GET("pacientes/{pacienteId}")
    fun getPacienteDetails(@Path("pacienteId") pacienteId: Int): Call<Paciente>

    @POST("pacientes/modificar/{id}")
    fun modificarPaciente(@Path("id") pacienteId: Int, @Body paciente: Paciente): Call<Paciente>

    @POST("pacientes/nuevo")
    fun crearPaciente(@Body paciente: Paciente): Call<Paciente>

    @GET("citas")
    fun getCitas(): Call<List<Cita>>

    @POST("citas/modificar/{id}")
    fun modificarCita(@Path("id") citaId: Int, @Body cita: Cita): Call<Cita>

    @POST("citas/nuevo")
    fun crearCita(@Body cita: Cita): Call<Cita>

    @GET("citas/{citaId}")
    fun getCitaDetails(@Path("citaId") citaId: Int): Call<Cita>

}