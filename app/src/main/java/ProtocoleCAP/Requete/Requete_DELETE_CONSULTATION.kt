package ProtocoleCAP.Requete

import kotlinx.serialization.Serializable
import protocol.RequeteCAP
class Requete_DELETE_CONSULTATION (
     idConsultation : Int
) : RequeteCAP() {

    companion object {
        private const val serialVersionUID = 1L
    }

    var idConsultation: Int = idConsultation
        private set


}

