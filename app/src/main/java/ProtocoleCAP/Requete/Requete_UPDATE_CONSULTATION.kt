package ProtocoleCAP.Requete


import protocol.RequeteCAP
import java.io.Serializable
import java.time.LocalDate

class Requete_UPDATE_CONSULTATION(
    private val idConsultation: Int?,
    private val nouvelleDate: LocalDate? = null,
    private val nouvelleHeure: String? = null,

    private val idPatient: Int? = null,
    private val nouvelleRaison: String? = null

) : RequeteCAP(), Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }


    fun getIdConsultation(): Int? = idConsultation
    fun getNouvelleDate(): LocalDate? = nouvelleDate
    fun getNouvelleHeure(): String? = nouvelleHeure
    fun getIdPatient(): Int? = idPatient
    fun getNouvelleRaison(): String? = nouvelleRaison
}