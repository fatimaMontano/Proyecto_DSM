package com.example.vet_santabarbara.adapters

import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vet_santabarbara.R
import com.example.vet_santabarbara.models.Cita

class CitaAdapter(private val citas: List<Cita>, val onItemClickListener: (Cita) -> Unit) : RecyclerView.Adapter<CitaAdapter.CitaViewHolder>() {

    class CitaViewHolder(val view: View, val onItemClickListener: (Cita) -> Unit) : RecyclerView.ViewHolder(view) {
        // Referencias a los elementos de la UI del CardView, por ejemplo:
        private val pacienteTextView: TextView = view.findViewById(R.id.pacienteTextView)
        private val propietarioTextView: TextView = view.findViewById(R.id.propietarioTextView)
        private val fechaTextView: TextView = view.findViewById(R.id.fechaTextView)
        private val doctorTextView: TextView = view.findViewById(R.id.doctorTextView)

        // Método para asociar datos del objeto Doctor con elementos de la UI
        fun bind(cita: Cita) {
            pacienteTextView.text = cita.paciente
            propietarioTextView.text = cita.propietario
            fechaTextView.text = cita.fecha_cita + cita.hora_cita
            doctorTextView.text = "Doctor: " +  cita.doctor
            itemView.setOnClickListener {
                // Llama al listener cuando se hace clic en el CardView
                onItemClickListener.invoke(cita)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_cita, parent, false)
        return CitaViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: CitaViewHolder, position: Int) {
        holder.bind(citas[position])
    }

    override fun getItemCount() = citas.size
}
