package ui.main.adapter

import android.graphics.Color
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

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvConsultation: TextView =
            itemView.findViewById(R.id.tvConsultation)

        init {
            itemView.setOnClickListener {
                val oldPos = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(oldPos)
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_consultation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val c = consultations[position]

        holder.tvConsultation.text =
            "📅 ${c.getDate()}  ⏰ ${c.getHour()}  👤 ${c.getPatient_id()}"

        holder.itemView.setBackgroundColor(
            if (position == selectedPosition)
                Color.parseColor("#553366FF")
            else
                Color.TRANSPARENT
        )
    }

    override fun getItemCount() = consultations.size

    fun getSelectedConsultation(): Consultation? =
        if (selectedPosition != RecyclerView.NO_POSITION)
            consultations[selectedPosition]
        else null
}
