package ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.kotlinprojet.R
import model.entity.Consultation

class ConsultationAdapter(
    private val consultations: List<Consultation>
) : RecyclerView.Adapter<ConsultationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.tvConsultation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_consultation, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = consultations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val c = consultations[position]
        holder.text.text =
            "ID:${c.getId()} | Patient:${c.getPatient_id() ?: "Libre"} | ${c.getDate()} ${c.getHour()}"
    }
}