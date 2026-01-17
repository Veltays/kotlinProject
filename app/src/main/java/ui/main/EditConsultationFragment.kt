package ui.main

import ProtocoleCAP.Requete.Requete_UPDATE_CONSULTATION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import be.kotlinprojet.R
import kotlinx.coroutines.launch
import model.entity.Consultation
import network.ConnectServer
import java.time.LocalDate

class EditConsultationFragment : Fragment() {

    // ==============================
    // VARIABLES
    // ==============================

    private lateinit var consultation: Consultation

    private lateinit var etDate: EditText
    private lateinit var etHour: EditText
    private lateinit var etReason: EditText
    private lateinit var etPatient: EditText
    private lateinit var btnSave: Button

    // Pour l’instant : patient mock (on branchera le vrai spinner après)

    // ==============================
    // LIFECYCLE
    // ==============================

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_edit_consultation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ==============================
        // RÉCUP ARGUMENT
        // ==============================

        val arg = arguments?.getSerializable("consultation")
        if (arg == null || arg !is Consultation) {
            Toast.makeText(requireContext(), "Erreur : consultation manquante", Toast.LENGTH_LONG).show()
            requireActivity().onBackPressed()
            return
        }
        consultation = arg

        // ==============================
        // VIEWS
        // ==============================

        etDate = view.findViewById(R.id.etDate)
        etHour = view.findViewById(R.id.etHour)
        etReason = view.findViewById(R.id.etReason)
        etPatient = view.findViewById(R.id.etPatientId)
        btnSave = view.findViewById(R.id.btnSave)

        // ==============================
        // PRÉ-REMPLISSAGE
        // ==============================

        etDate.setText(consultation.getDate().toString())
        etHour.setText(consultation.getHour())

        // ==============================
        // LOGIQUE MÉTIER
        // ==============================

        val isAssigned = consultation.getPatient_id() != 0

        if (isAssigned) {
            // consultation déjà attribuée → modifier date/heure
            etPatient.visibility = View.GONE
            etReason.visibility = View.GONE

            etDate.visibility = View.VISIBLE
            etHour.visibility = View.VISIBLE
        } else {
            // consultation libre → attribuer patient/raison
            etPatient.visibility = View.VISIBLE
            etReason.visibility = View.VISIBLE

            etDate.visibility = View.GONE
            etHour.visibility = View.GONE
        }

        // ==============================
        // SAVE
        // ==============================

        btnSave.setOnClickListener {
            onSaveClicked(isAssigned)
        }
    }

    // ==============================
    // SAVE LOGIC
    // ==============================

    private fun onSaveClicked(isAssigned: Boolean) {

        lifecycleScope.launch {

            val requete = if (isAssigned) {
                // 🔹 consultation attribuée → modifier date / heure
                Requete_UPDATE_CONSULTATION(
                    idConsultation = consultation.getId(),
                    nouvelleDate = LocalDate.parse(etDate.text.toString()),
                    nouvelleHeure = etHour.text.toString()
                )
            } else {
                // 🔹 consultation libre → attribuer patient / raison
                Requete_UPDATE_CONSULTATION(
                    idConsultation = consultation.getId(),
                    idPatient = etPatient.text.toString().toIntOrNull(),
                    nouvelleRaison = etReason.text.toString(),
                    nouvelleHeure = consultation.getHour()  // ✅ heure EXISTANTE
                )
            }

            val success = ConnectServer.updateConsultation(requete)

            if (success) {
                Toast.makeText(requireContext(), "Consultation mise à jour", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(requireContext(), "Erreur lors de la mise à jour", Toast.LENGTH_LONG).show()
            }
        }
    }

}
