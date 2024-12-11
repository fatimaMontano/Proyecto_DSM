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
import com.example.vet_santabarbara.models.Doctor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetalleDoctorActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_doctor)

        val doctorId = intent.getIntExtra("doctorId", -1) // -1 es un valor predeterminado en caso de que no se encuentre el extra
        obtenerDetallesDelDoctor(doctorId)

    }

    fun realizarModificacionDoctor(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.216.221.198:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        // Obtener valores necesarios para la modificación
        val nombre = findViewById<EditText>(R.id.nombreDoctor)
        val especialidad = findViewById<EditText>(R.id.especialidadDoctor)
        val direccion = findViewById<EditText>(R.id.direccionDoctor)
        val telefono = findViewById<EditText>(R.id.telefonoDoctor)
        val email = findViewById<EditText>(R.id.emailDoctor)
        val horario = findViewById<EditText>(R.id.horarioDoctor)
        val id = findViewById<TextView>(R.id.idDoctor)

        // Crear un objeto Doctor con los nuevos valores
        val doctorModificado = Doctor(Integer.parseInt(id.text.toString()), nombre.text.toString(), especialidad.text.toString(), "1997-11-23", direccion.text.toString(), telefono.text.toString(), horario.text.toString(), email.text.toString())

        // Realizar la solicitud POST
        apiService.modificarDoctor(Integer.parseInt(id.text.toString()), doctorModificado).enqueue(object : Callback<Doctor> {
            override fun onResponse(call: Call<Doctor>, response: Response<Doctor>) {
                if (response.isSuccessful) {
                    // Manejar la respuesta exitosa
                    Toast.makeText(this@DetalleDoctorActivity, "Doctor modificado exitosamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent()
                    intent.putExtra("actualizado", true)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    // Manejar la respuesta con error
                    Toast.makeText(this@DetalleDoctorActivity, "Error al modificar el doctor", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Doctor>, t: Throwable) {
                // Manejar fallos en la conexión
                Log.e("Network", "Fallo en la conexión: ${t.message}", t)
            }
        })
    }

    private fun obtenerDetallesDelDoctor(doctorId: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.216.221.198:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getDoctorDetails(doctorId).enqueue(object : Callback<Doctor> {
            override fun onResponse(call: Call<Doctor>, response: Response<Doctor>) {
                if (response.isSuccessful) {
                    val doctorDetails = response.body()
                    if (doctorDetails != null) {
                        val nombreTextView: TextView = findViewById(R.id.idDoctor)
                        val nombreCompleto: EditText = findViewById(R.id.nombreDoctor)
                        val domicilio: EditText = findViewById(R.id.direccionDoctor)
                        val correo: EditText = findViewById(R.id.emailDoctor)
                        val telefono: EditText = findViewById(R.id.telefonoDoctor)
                        val horario: EditText = findViewById(R.id.horarioDoctor)
                        val especialidad: EditText = findViewById(R.id.especialidadDoctor)

                        // Asigna los datos del doctor a las vistas
                        nombreTextView.text = doctorDetails.id_doctor.toString()
                        nombreCompleto.setText(doctorDetails.nombre_completo)
                        domicilio.setText(doctorDetails.domicilio)
                        correo.setText(doctorDetails.correo)
                        telefono.setText(doctorDetails.telefono)
                        horario.setText(doctorDetails.horario)
                        especialidad.setText(doctorDetails.especialidad)
                    }
                }
            }

            override fun onFailure(call: Call<Doctor>, t: Throwable) {
                // Manejar fallos al obtener detalles del doctor
                Log.e("Network", "Fallo al obtener detalles del doctor: ${t.message}", t)
            }
        })
    }

}