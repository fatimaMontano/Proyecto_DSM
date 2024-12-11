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
import com.example.vet_santabarbara.models.Cita
import com.example.vet_santabarbara.models.Paciente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetalleCitaActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_cita)

        val citaId = intent.getIntExtra("citaId", -1) // -1 es un valor predeterminado en caso de que no se encuentre el extra
        obtenerDetalles(citaId)
    }

    fun modificar(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.216.221.198:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        // Obtener valores necesarios para la modificación
        val id_doctor = findViewById<EditText>(R.id.id_doctor)
        val id_paciente = findViewById<EditText>(R.id.id_paciente)
        val fecha = findViewById<EditText>(R.id.fecha)
        val hora = findViewById<EditText>(R.id.hora)
        val id = findViewById<TextView>(R.id.CItaNameTV)

        // Crear un objeto Doctor con los nuevos valores
        val citaModificada = Cita(
            id_doctor = Integer.parseInt(id_doctor.text.toString()),
            id_paciente = Integer.parseInt(id_paciente.text.toString()),
            fecha = fecha.text.toString(),
            hora = hora.text.toString()
        )
        // Realizar la solicitud POST
        apiService.modificarCita(Integer.parseInt(id.text.toString()), citaModificada).enqueue(object : Callback<Cita> {
            override fun onResponse(call: Call<Cita>, response: Response<Cita>) {
                if (response.isSuccessful) {
                    // Manejar la respuesta exitosa
                    Toast.makeText(this@DetalleCitaActivity, "Cita modificado exitosamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent()
                    intent.putExtra("actualizado", true)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    // Manejar la respuesta con error
                    Toast.makeText(this@DetalleCitaActivity, "Error al modificar cita", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Cita>, t: Throwable) {
                // Manejar fallos en la conexión
                Log.e("Network", "Fallo en la conexión: ${t.message}", t)
            }
        })
    }

    private fun obtenerDetalles(citaId: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.216.221.198:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getCitaDetails(citaId).enqueue(object : Callback<Cita> {
            override fun onResponse(call: Call<Cita>, response: Response<Cita>) {
                if (response.isSuccessful) {
                    val citaDetails = response.body()
                    if (citaDetails != null) {
                        val nombreTextView: TextView = findViewById(R.id.CItaNameTV)
                        val idDoctorTextView: TextView = findViewById(R.id.id_doctor)
                        val idPacienteTextView: TextView = findViewById(R.id.id_paciente)
                        val doctorTextView: TextView = findViewById(R.id.doctor)
                        val pacienteTextView: TextView = findViewById(R.id.paciente)
                        val fechaTextView: TextView = findViewById(R.id.fecha)
                        val horaTextView: TextView = findViewById(R.id.hora)


                        // Asigna los datos del doctor a las vistas
                        nombreTextView.text = citaDetails.id_cita.toString()
                        idDoctorTextView.text = citaDetails.id_doctor.toString()
                        idPacienteTextView.text = citaDetails.id_cita.toString()
                        doctorTextView.text = citaDetails.doctor.toString()
                        pacienteTextView.text = citaDetails.paciente.toString()
                        fechaTextView.text = citaDetails.fecha_cita.toString()
                        horaTextView.text = citaDetails.hora_cita.toString()
                    }
                }
            }
            override fun onFailure(call: Call<Cita>, t: Throwable) {
                // Manejar fallos al obtener detalles del doctor
                Log.e("Network", "Fallo al obtener detalles del paciente: ${t.message}", t)
            }
        })
    }

}