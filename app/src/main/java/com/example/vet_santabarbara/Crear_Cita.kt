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
import com.example.vet_santabarbara.models.Cita
import com.example.vet_santabarbara.models.Paciente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Crear_Cita : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cita)
    }
    fun crearCita(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.216.221.198:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        // Obtener valores necesarios para la modificación
        val doctor = findViewById<EditText>(R.id.idDoctorCita)
        val paciente = findViewById<EditText>(R.id.idPacienteCita)
        val fecha = findViewById<EditText>(R.id.fechaCita)
        val hora = findViewById<EditText>(R.id.horaCita)
        // Crear un objeto Doctor con los nuevos valores
        val cita = Cita(
            id_paciente =  Integer.parseInt(paciente.text.toString()),
            id_doctor =  Integer.parseInt(doctor.text.toString()),
            fecha = fecha.text.toString(),
            hora = hora.text.toString(),
        )

        // Realizar la solicitud POST
        apiService.crearCita(cita).enqueue(object :
            Callback<Cita> {
            override fun onResponse(call: Call<Cita>, response: Response<Cita>) {
                if (response.isSuccessful) {
                    // Manejar la respuesta exitosa
                    Toast.makeText(this@Crear_Cita, "Cita creado exitosamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent()
                    intent.putExtra("actualizado", true)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    // Manejar la respuesta con error
                    Toast.makeText(this@Crear_Cita, "Error al crear cita", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Cita>, t: Throwable) {
                // Manejar fallos en la conexión
                Log.e("Network", "Fallo en la conexión: ${t.message}", t)
            }
        })
    }
}