package ProtocoleCAP.Reponse

import model.entity.Consultation
import protocol.Reponse
import java.io.Serializable
import java.util.ArrayList

class Reponse_SEARCH_CONSULTATIONS (
    var consultationsList: ArrayList<Consultation> = ArrayList()
) : Reponse, Serializable {

    private val valide: Boolean = true


    companion object {
        private const val serialVersionUID = 1L

    }

    fun isValide(): Boolean = valide
}
