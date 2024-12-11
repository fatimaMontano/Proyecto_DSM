package com.example.vet_santabarbara.adapters

import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vet_santabarbara.R
import com.example.vet_santabarbara.models.Paciente

class PacienteAdapter(private val pacientes: List<Paciente>, val onItemClickListener: (Paciente) -> Unit) : RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder>() {

    class PacienteViewHolder(val view: View, val onItemClickListener: (Paciente) -> Unit) : RecyclerView.ViewHolder(view) {
        // Referencias a los elementos de la UI del CardView, por ejemplo:
        private val pacienteTextView: TextView = view.findViewById(R.id.pacienteTextView)
        private val propietarioTextView: TextView = view.findViewById(R.id.propietarioTextView)

        // Método para asociar datos del objeto Doctor con elementos de la UI
        fun bind(paciente: Paciente) {
            pacienteTextView.text = paciente.nombre_completo
            propietarioTextView.text = paciente.nombre_completo_propietario
            itemView.setOnClickListener {
                // Llama al listener cuando se hace clic en el CardView
                onItemClickListener.invoke(paciente)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_paciente, parent, false)
        return PacienteViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        holder.bind(pacientes[position])
    }

    override fun getItemCount() = pacientes.size
}
