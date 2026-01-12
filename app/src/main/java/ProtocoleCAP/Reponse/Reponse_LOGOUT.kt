package ProtocoleCAP.Reponse

import protocol.Reponse
import java.io.Serializable

class Reponse_LOGOUT(
    private val valide: Boolean,
    idConnexion: Int?
) : Reponse {

    companion object {
        private const val serialVersionUID: Long = 1L
    }


    fun isValide(): Boolean {
        return valide
    }
}
