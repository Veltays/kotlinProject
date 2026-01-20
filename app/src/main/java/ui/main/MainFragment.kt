package ui.main

import ProtocoleCAP.Requete.Requete_ADD_CONSULTATION
import ProtocoleCAP.Requete.Requete_ADD_PATIENT
import ProtocoleCAP.Requete.Requete_DELETE_CONSULTATION
import ProtocoleCAP.Requete.Requete_SEARCH_CONSULTATIONS
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.kotlinprojet.R
import kotlinx.coroutines.launch
import network.ConnectServer
import ui.MainActivity
import ui.main.adapter.ConsultationAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var consultationAdapter: ConsultationAdapter
    private lateinit var deleteButton: Button
    private lateinit var updateButton : Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as? MainActivity)?.setupToolbar(toolbar)

        recyclerView = view.findViewById(R.id.rvConsultations)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        consultationAdapter = ConsultationAdapter(emptyList())
        recyclerView.adapter = consultationAdapter

        deleteButton = view.findViewById(R.id.button3)
        deleteButton.setOnClickListener {
            onDeleteClicked()
        }

        updateButton = view.findViewById(R.id.button)
        updateButton.setOnClickListener {
            onUpdateClicked()
        }


    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.enableDrawer()
        if (ConnectServer.isLogged()) {
            showConsultations()
        }
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

        val etDate = view.findViewById<EditText>(R.id.etDate)
        val etHeure = view.findViewById<EditText>(R.id.etHeure)
        val etDuree = view.findViewById<EditText>(R.id.etDuree)
        val etNombre = view.findViewById<EditText>(R.id.etNombre)

        AlertDialog.Builder(requireContext())
            .setTitle("Ajouter une consultation")
            .setView(view)
            .setPositiveButton("OK") { _, _ ->

                val date = etDate.text.toString().trim()
                val heure = etHeure.text.toString().trim()
                val duree = etDuree.text.toString().trim()
                val nombre = etNombre.text.toString().trim()

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
                        AlertDialog.Builder(requireContext())
                            .setTitle("succès")
                            .setMessage("consultation(s) ajoutée(s) avec succès!")
                            .setPositiveButton("OK", null)
                            .show()
                    } else {
                        Log.e("ADD_CONSULTATION", "Erreur ajout consultation")
                        AlertDialog.Builder(requireContext())
                            .setTitle("erreur")
                            .setMessage("erreur lors de l'ajout")
                            .setPositiveButton("OK", null)
                            .show()
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
            .setTitle("Ajouter un patient")
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
                        AlertDialog.Builder(requireContext())
                            .setTitle("success")
                            .setMessage("patient ajouté avec succès")
                            .setPositiveButton("ok!",null)
                            .show()
                    } else {
                        Log.e("ADD_PATIENT", "Erreur ajout patient")
                        AlertDialog.Builder(requireContext())
                            .setTitle("erreur")
                            .setMessage("erreur lors de l'ajout du patient")
                            .setPositiveButton("ok",null)
                            .show()
                    }
                }
            }
            .setNegativeButton("Annuler", null)
            .show()
    }


    fun showConsultations() {

        lifecycleScope.launch {
            try {
                val consultations = ConnectServer.searchConsultation(
                    Requete_SEARCH_CONSULTATIONS(null, null)
                )

                // fragment encore attaché ?
                if (!isAdded || view == null) {
                    return@launch
                }

                consultationAdapter = ConsultationAdapter(consultations)
                recyclerView.apply {
                    adapter = consultationAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                    setHasFixedSize(true)
                }

            } catch (e: Exception) {
                Log.e("UI", "EXCEPTION dans showConsultations()", e)
            }
        }
    }


    fun onDeleteClicked() {

        val selected = consultationAdapter.getSelectedConsultation()

        if (selected == null) {
            AlertDialog.Builder(requireContext())
                .setTitle("Erreur")
                .setMessage("Veuillez sélectionner une consultation")
                .setPositiveButton("OK", null)
                .show()
            return
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Supprimer")
            .setMessage("Supprimer la consultation du ${selected.getDate()} à ${selected.getHour()} ?")
            .setPositiveButton("Oui") { _, _ ->
                lifecycleScope.launch {
                    val requete = Requete_DELETE_CONSULTATION(selected.getId())
                    ConnectServer.deleteConsultation(requete)
                    showConsultations()
                }
            }
            .setNegativeButton("Annuler", null)
            .show()
    }

    fun onUpdateClicked() {

        val selected = consultationAdapter.getSelectedConsultation()

        if (selected == null) {
            AlertDialog.Builder(requireContext())
                .setTitle("Erreur")
                .setMessage("Veuillez sélectionner une consultation")
                .setPositiveButton("OK", null)
                .show()
            return
        }

        val bundle = Bundle().apply {
            putSerializable("consultation", selected)
        }

        findNavController().navigate(
            R.id.action_mainFragment_to_editConsultationFragment,
            bundle
        )
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
