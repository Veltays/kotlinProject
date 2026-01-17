package ProtocoleCAP.Requete


import protocol.RequeteCAP
import java.io.Serializable
import java.time.LocalDate

class Requete_UPDATE_CONSULTATION(
    val idConsultation: Int?,
    val newDate: LocalDate? = null,
    val newHour: String? = null,
    val patientId: Int? = null,
    val reason: String? = null

) : RequeteCAP(), Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return "Requete_UPDATE_CONSULTATION(" +
                "idConsultation=$idConsultation, " +
                "newDate=$newDate, newHour=$newHour, " +
                "patientId=$patientId, reason=$reason)"
    }
}