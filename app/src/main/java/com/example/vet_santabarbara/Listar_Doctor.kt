package com.example.vet_santabarbara;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vet_santabarbara.api.ApiService
import com.example.vet_santabarbara.models.Doctor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.vet_santabarbara.adapters.DoctorAdapter

class Listar_Doctor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_doctor)

        val crearDoctorBtn: Button = findViewById(R.id.crearCita)
        crearDoctorBtn.setOnClickListener {
            val intent = Intent(this, Crear_Doctor::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.216.221.198:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java  )
        val recyclerView: RecyclerView = findViewById(R.id.CitaRecyclerView)

        apiService.getDoctores().enqueue(object : Callback<List<Doctor>> {
            override fun onResponse(call: Call<List<Doctor>>, response: Response<List<Doctor>>) {
                if (response.isSuccessful) {
                    val doctorList = response.body() ?: emptyList()
                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = LinearLayoutManager(this@Listar_Doctor)
//                    recyclerView.adapter = DoctorAdapter(doctorList)

                    // Declaración de onItemClickListener aquí
                    val onItemClickListener: (Doctor) -> Unit = { selectedDoctor ->
                        // Abre la pantalla de detalle del doctor
                        val intent = Intent(this@Listar_Doctor, DetalleDoctorActivity::class.java)
                        intent.putExtra("doctorId", selectedDoctor.id_doctor)
                        startActivity(intent)
                    }
                    val doctorAdapter = DoctorAdapter(doctorList, onItemClickListener)
                    recyclerView.adapter = doctorAdapter
                }
            }

            override fun onFailure(call: Call<List<Doctor>>, t: Throwable) {
                // Manejar fallos
//                Toast.makeText(this@Listar_Doctor, "Fallo en la conexión: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("Network", "Fallo en la conexión: ${t.message}", t)

            }
        })
    }
}
