package ProtocoleCAP.Requete

import protocol.RequeteCAP
import java.io.Serializable
import java.time.LocalDate

class Requete_SEARCH_CONSULTATIONS(
    idPatient: Int?,
    date: LocalDate?
) : RequeteCAP(), Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    // 🔥 Integer Java → Int? Kotlin
    var idPatient: Int? = idPatient
        private set

    var date: LocalDate? = date
        private set

    override fun toString(): String {
        return "Requete_SEARCH_CONSULTATIONS(idPatient=$idPatient, date=$date)"
    }
}
