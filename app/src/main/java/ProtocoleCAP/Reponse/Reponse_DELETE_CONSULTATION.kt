package ProtocoleCAP.Reponse

import protocol.Reponse

class Reponse_DELETE_CONSULTATION (
    private var valide : Boolean
) : Reponse
{
    companion object {
        private const val serialVersionUID: Long = 1L
    }

    fun isValide(): Boolean = valide
}