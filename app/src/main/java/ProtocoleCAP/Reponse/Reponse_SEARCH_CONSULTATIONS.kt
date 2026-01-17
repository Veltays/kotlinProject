package ProtocoleCAP.Reponse

import model.entity.Consultation
import protocol.Reponse
import java.io.Serializable
import java.util.ArrayList

class Reponse_SEARCH_CONSULTATIONS : Reponse, Serializable {

    private val valide: Boolean = true


    companion object {
        private const val serialVersionUID = 1L

    }

    private val consultationsList: ArrayList<Consultation> = ArrayList()

    fun addConsultation(c: Consultation) {
        consultationsList.add(c)
    }

    fun getConsultationsList(): ArrayList<Consultation> {
        return consultationsList
    }
    fun isValide(): Boolean = valide
}
