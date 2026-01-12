package protocol

import java.io.Serializable

interface Requete : Serializable {
    fun getIdConnexion(): Int?
    fun setIdConnexion(idConnexion: Int?)
}
