package ProtocoleCAP.Requete

import protocol.RequeteCAP
class Requete_DELETE_CONSULTATION (
    idConsultation: Int?
) : RequeteCAP() {

    companion object {
        private const val serialVersionUID = 1L
    }

    var idConsultation: Int? = idConsultation
        private set


}

