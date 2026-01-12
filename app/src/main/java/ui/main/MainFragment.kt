package ui.main

import ProtocoleCAP.Requete.Requete_ADD_CONSULTATION
import ProtocoleCAP.Requete.Requete_ADD_PATIENT
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import be.kotlinprojet.R
import kotlinx.coroutines.launch
import network.ConnectServer
import ui.MainActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as? MainActivity)?.setupToolbar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.enableDrawer()
    }

    override fun onPause() {
        super.onPause()
        (activity as? MainActivity)?.disableDrawer()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    fun showAddConsultationDialog() {

        val view = layoutInflater.inflate(
            R.layout.dialog_add_consultation,
            null
        )

        // 🔹 Récupération des champs
        val etDate = view.findViewById<EditText>(R.id.etDate)
        val etHeure = view.findViewById<EditText>(R.id.etHeure)
        val etDuree = view.findViewById<EditText>(R.id.etDuree)
        val etNombre = view.findViewById<EditText>(R.id.etNombre)

        AlertDialog.Builder(requireContext())
            .setTitle("Ajouter une consultation")
            .setView(view)
            .setPositiveButton("OK") { _, _ ->

                // 🔹 Lecture des valeurs saisies
                val date = etDate.text.toString().trim()
                val heure = etHeure.text.toString().trim()
                val duree = etDuree.text.toString().trim()
                val nombre = etNombre.text.toString().trim()

                // 🔹 Affichage (test / debug / cours)
                Log.d("MainFragment", "Date=$date Heure=$heure Durée=$duree Nombre=$nombre")



                lifecycleScope.launch {

                    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

                    val date = try {
                        LocalDate.parse(date,formatter)
                    } catch (e: Exception) {
                        Log.e("ADD_CONSULTATION", "Date invalide")
                        return@launch
                    }



                    val nombre = nombre.toIntOrNull()
                    if (nombre == null || nombre <= 0) {
                        Log.e("ADD_CONSULTATION", "Nombre invalide")
                        return@launch
                    }

                    val requete = Requete_ADD_CONSULTATION(
                        date = date,
                        heure = heure,
                        duree = duree,
                        nombreConsultations = nombre
                    )

                    val success = ConnectServer.addConsultation(requete)

                    if (success) {
                        Log.d("ADD_CONSULTATION", "Consultation ajoutée")
                    } else {
                        Log.e("ADD_CONSULTATION", "Erreur ajout consultation")
                    }
                }
            }
            .setNegativeButton("Annuler", null)
            .show()
    }


    fun showAddPatientDialog() {

        val view = layoutInflater.inflate(
            R.layout.dialog_add_patient,
            null
        )

        val etNom = view.findViewById<EditText>(R.id.etNom)
        val etPrenom = view.findViewById<EditText>(R.id.etPrenom)

        AlertDialog.Builder(requireContext())
            .setTitle("➕ Ajouter un patient")
            .setView(view)
            .setPositiveButton("OK") { _, _ ->

                val nom = etNom.text.toString().trim()
                val prenom = etPrenom.text.toString().trim()

                Log.d("MainFragment", "Nom=$nom Prenom=$prenom")

                if (nom.isEmpty() || prenom.isEmpty()) {
                    Log.e("ADD_PATIENT", "Champs vides")
                    return@setPositiveButton
                }

                lifecycleScope.launch {
                    val requete = Requete_ADD_PATIENT(
                        firstName = prenom,
                        lastName = nom
                    )

                    val idPatient = ConnectServer.addPatient(requete)

                    if (idPatient != -1) {
                        Log.d("ADD_PATIENT", "Patient ajouté id=$idPatient")
                    } else {
                        Log.e("ADD_PATIENT", "Erreur ajout patient")
                    }
                }
            }
            .setNegativeButton("Annuler", null)
            .show()
    }


    fun handlelogout() {
        lifecycleScope.launch {
            val success = ConnectServer.logout()
            if (success) {
                requireActivity().supportFragmentManager.popBackStack()
                (activity as MainActivity).disableDrawer()
            }
        }
    }



}
