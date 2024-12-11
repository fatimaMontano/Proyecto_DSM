package com.example.vet_santabarbara

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.vet_santabarbara.api.ApiService
import com.example.vet_santabarbara.models.Doctor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Crear_Doctor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_doctor)
    }

    fun crearDoctor(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.216.221.198:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        // Obtener valores necesarios para la modificación
        val nombre = findViewById<EditText>(R.id.id_doctor)
        val especialidad = findViewById<EditText>(R.id.especialidad)
        val direccion = findViewById<EditText>(R.id.doctor)
        val telefono = findViewById<EditText>(R.id.paciente)
        val email = findViewById<EditText>(R.id.id_paciente)
        val horario = findViewById<EditText>(R.id.horario)

        // Crear un objeto Doctor con los nuevos valores
        val doctor = Doctor(
            nombre_completo =  nombre.text.toString(),
            especialidad = especialidad.text.toString(),
            fecha_nac = "1997-11-23",
            domicilio = direccion.text.toString(),
            telefono = telefono.text.toString(),
            horario = horario.text.toString(),
            correo = email.text.toString()
        )

        // Realizar la solicitud POST
        apiService.crearDoctor(doctor).enqueue(object :
            Callback<Doctor> {
            override fun onResponse(call: Call<Doctor>, response: Response<Doctor>) {
                if (response.isSuccessful) {
                    // Manejar la respuesta exitosa
                    Toast.makeText(this@Crear_Doctor, "Doctor creado exitosamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent()
                    intent.putExtra("actualizado", true)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    // Manejar la respuesta con error
                    Toast.makeText(this@Crear_Doctor, "Error al crear el doctor", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Doctor>, t: Throwable) {
                // Manejar fallos en la conexión
                Log.e("Network", "Fallo en la conexión: ${t.message}", t)
            }
        })
    }
}