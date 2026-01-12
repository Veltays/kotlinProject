package ProtocoleCAP.Reponse

import protocol.Reponse
import java.io.Serializable

class Reponse_ADD_CONSULTATION(
    private val valide: Boolean
) : Reponse, Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    fun isValide(): Boolean = valide
}
