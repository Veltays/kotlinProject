package ProtocoleCAP.Requete

import protocol.RequeteCAP
import java.io.Serializable
import java.time.LocalDate

class Requete_ADD_CONSULTATION(
    date: LocalDate,
    heure: String,
    duree: String,
    nombreConsultations: Int
) : RequeteCAP(), Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    var date: LocalDate = date
    private set

    var heure: String = heure
        private set

    var duree: String = duree
        private set

    var nombreConsultations: Int? = nombreConsultations
        private set

    init {
        this.date = date
        this.heure = heure
        this.duree = duree
        this.nombreConsultations = nombreConsultations
    }

    override fun toString(): String {
        return "Requete_ADD_CONSULTATION(" +
                "date=$date, heure='$heure', duree='$duree', nombreConsultations=$nombreConsultations)"
    }
}
