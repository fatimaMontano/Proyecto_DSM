package com.example.vet_santabarbara;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vet_santabarbara.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.vet_santabarbara.adapters.PacienteAdapter
import com.example.vet_santabarbara.models.Paciente

class Listar_Paciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_paciente)

        val crearPacienteBtn: Button = findViewById(R.id.crearCita)
        crearPacienteBtn.setOnClickListener {
            val intent = Intent(this, Crear_Paciente::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://18.216.221.198:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val recyclerView: RecyclerView = findViewById(R.id.CitaRecyclerView)

        apiService.getPacientes().enqueue(object : Callback<List<Paciente>> {
            override fun onResponse(call: Call<List<Paciente>>, response: Response<List<Paciente>>) {
                if (response.isSuccessful) {
                    val pacienteList = response.body() ?: emptyList()
                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = LinearLayoutManager(this@Listar_Paciente)
//                    recyclerView.adapter = DoctorAdapter(doctorList)

                    // Declaración de onItemClickListener aquí
                    val onItemClickListener: (Paciente) -> Unit = { selectedPaciente ->
                        // Abre la pantalla de detalle del doctor
                        val intent = Intent(this@Listar_Paciente, DetallePacienteActivity::class.java)
                        intent.putExtra("pacienteId", selectedPaciente.id_paciente)
                        startActivity(intent)
                    }
                    val pacienteAdapter = PacienteAdapter(pacienteList, onItemClickListener)
                    recyclerView.adapter = pacienteAdapter
                }
            }

            override fun onFailure(call: Call<List<Paciente>>, t: Throwable) {
                // Manejar fallos
//                Toast.makeText(this@Listar_Doctor, "Fallo en la conexión: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("Network", "Fallo en la conexión: ${t.message}", t)

            }
        })
    }
}
