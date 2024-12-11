package com.example.vet_santabarbara

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vet_santabarbara.api.ApiService
import com.example.vet_santabarbara.models.Paciente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetallePacienteActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_paciente)

        val pacienteId = intent.getIntExtra("pacienteId", -1) // -1 es un valor predeterminado en caso de que no se encuentre el extra
        obtenerDetalles(pacienteId)
    }

    fun modificar(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.216.221.198:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        // Obtener valores necesarios para la modificación
        val nombreCompleto = findViewById<EditText>(R.id.nombreCompletoPaciente)
        val nombreCompletoPropietario = findViewById<EditText>(R.id.nombreCompletoPropietario)
        val color = findViewById<EditText>(R.id.color)
        val peso = findViewById<EditText>(R.id.peso)
        val raza = findViewById<EditText>(R.id.raza)
        val especie = findViewById<EditText>(R.id.especie)
        val direccion = findViewById<EditText>(R.id.domicilioPaciente)
        val telefono = findViewById<EditText>(R.id.telefonoPaciente)
        val email = findViewById<EditText>(R.id.correoPaciente)

        val id = findViewById<TextView>(R.id.idPaciente)

        // Crear un objeto Doctor con los nuevos valores
        val pacienteModificado = Paciente(
            id_paciente = Integer.parseInt(id.text.toString()),
            color = color.text.toString(),
            correo = email.text.toString(),
            deshabilitado = 0,
            domicilio = direccion.text.toString(),
            especie = especie.text.toString(),
            nombre_completo = nombreCompleto.text.toString(),
            nombre_completo_propietario = nombreCompletoPropietario.text.toString(),
            peso = Integer.parseInt(peso.text.toString()),
            raza = raza.text.toString(),
            telefono = telefono.text.toString(),
            contrasenia = "cambiame",
            fecha_nac_mascota = "2023-11-1997"
        )
        // Realizar la solicitud POST
        apiService.modificarPaciente(Integer.parseInt(id.text.toString()), pacienteModificado).enqueue(object : Callback<Paciente> {
            override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                if (response.isSuccessful) {
                    // Manejar la respuesta exitosa
                    Toast.makeText(this@DetallePacienteActivity, "Paciente modificado exitosamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent()
                    intent.putExtra("actualizado", true)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    // Manejar la respuesta con error
                    Toast.makeText(this@DetallePacienteActivity, "Error al modificar el paciente", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Paciente>, t: Throwable) {
                // Manejar fallos en la conexión
                Log.e("Network", "Fallo en la conexión: ${t.message}", t)
            }
        })
    }

    private fun obtenerDetalles(pacienteId: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.216.221.198:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getPacienteDetails(pacienteId).enqueue(object : Callback<Paciente> {
            override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                if (response.isSuccessful) {
                    val pacienteDetails = response.body()
                    if (pacienteDetails != null) {
                        val nombreTextView: TextView = findViewById(R.id.idPaciente)
                        val nombreCompleto: EditText = findViewById(R.id.nombreCompletoPaciente)
                        val nombreCompletoPropietario: EditText = findViewById(R.id.nombreCompletoPropietario)
                        val domicilio: EditText = findViewById(R.id.domicilioPaciente)
                        val correo: EditText = findViewById(R.id.correoPaciente)
                        val telefono: EditText = findViewById(R.id.telefonoPaciente)
                        val color: EditText = findViewById(R.id.color)
                        val peso: EditText = findViewById(R.id.peso)
                        val raza: EditText = findViewById(R.id.raza)
                        val especie: EditText = findViewById(R.id.especie)

                        // Asigna los datos del doctor a las vistas
                        nombreTextView.text = pacienteDetails.id_paciente.toString()
                        nombreCompleto.setText(pacienteDetails.nombre_completo)
                        domicilio.setText(pacienteDetails.domicilio)
                        nombreCompletoPropietario.setText(pacienteDetails.nombre_completo_propietario)
                        correo.setText(pacienteDetails.correo)
                        telefono.setText(pacienteDetails.telefono)
                        color.setText(pacienteDetails.color)
                        peso.setText(pacienteDetails.peso.toString())
                        raza.setText(pacienteDetails.raza)
                        especie.setText(pacienteDetails.especie)

                    }
                }
            }
            override fun onFailure(call: Call<Paciente>, t: Throwable) {
                // Manejar fallos al obtener detalles del doctor
                Log.e("Network", "Fallo al obtener detalles del paciente: ${t.message}", t)
            }
        })
    }

}