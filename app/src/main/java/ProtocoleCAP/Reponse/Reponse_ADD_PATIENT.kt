package ProtocoleCAP.Reponse

import protocol.Reponse
import java.io.Serializable

class Reponse_ADD_PATIENT(
    val idAttribuer: Int
) : Reponse, Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }
}
