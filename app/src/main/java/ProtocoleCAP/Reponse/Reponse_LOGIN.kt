package ProtocoleCAP.Reponse

import protocol.Reponse

class Reponse_LOGIN(
    private var valide: Boolean,
    private var IdConnexion: Int?
) : Reponse {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    fun isValide(): Boolean = valide

    fun getIdConnexion(): Int? = IdConnexion

    fun setIdConnexion(idConnexion: Int?) {
        IdConnexion = idConnexion
    }
}
