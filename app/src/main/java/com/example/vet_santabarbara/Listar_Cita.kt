package com.example.vet_santabarbara;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vet_santabarbara.adapters.CitaAdapter
import com.example.vet_santabarbara.api.ApiService
import com.example.vet_santabarbara.models.Doctor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.vet_santabarbara.adapters.DoctorAdapter
import com.example.vet_santabarbara.models.Cita

class Listar_Cita : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_cita)

        val crearCitaBtn: Button = findViewById(R.id.crearCita)
        crearCitaBtn.setOnClickListener {
            val intent = Intent(this, Crear_Cita::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.216.221.198:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java  )
        val recyclerView: RecyclerView = findViewById(R.id.CitaRecyclerView)

        apiService.getCitas().enqueue(object : Callback<List<Cita>> {
            override fun onResponse(call: Call<List<Cita>>, response: Response<List<Cita>>) {
                if (response.isSuccessful) {
                    val citaList = response.body() ?: emptyList()
                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = LinearLayoutManager(this@Listar_Cita)

                    // Declaración de onItemClickListener aquí
                    val onItemClickListener: (Cita) -> Unit = { selectedCita ->
                        // Abre la pantalla de detalle del doctor
                        val intent = Intent(this@Listar_Cita, DetalleCitaActivity::class.java)
                        intent.putExtra("citaId", selectedCita.id_cita)
                        startActivity(intent)
                    }
                    val citaAdapter = CitaAdapter(citaList, onItemClickListener)
                    recyclerView.adapter = citaAdapter
                }
            }

            override fun onFailure(call: Call<List<Cita>>, t: Throwable) {
                // Manejar fallos
                Log.e("Network", "Fallo en la conexión: ${t.message}", t)

            }
        })
    }
}
