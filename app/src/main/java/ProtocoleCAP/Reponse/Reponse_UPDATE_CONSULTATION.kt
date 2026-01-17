package ProtocoleCAP.Reponse

import protocol.Reponse
import java.io.Serializable

class Reponse_UPDATE_CONSULTATION(
    private val valide: Boolean,
    private val message: String? = null
) : Reponse, Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    fun isValide(): Boolean = valide

    fun getMessage(): String? = message

    override fun toString(): String {
        return "Reponse_UPDATE_CONSULTATION(valide=$valide, message=$message)"
    }
}