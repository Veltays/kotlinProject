package ProtocoleCAP.Requete

import protocol.RequeteCAP
import java.io.Serializable

class Requete_ADD_PATIENT(
    firstName: String,
    lastName: String
) : RequeteCAP(), Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    var firstName: String = firstName
        private set(value) {
            require(value.isNotBlank()) { "Le prénom ne peut pas être vide." }
            field = value
        }

    var lastName: String = lastName
        private set(value) {
            require(value.isNotBlank()) { "Le nom ne peut pas être vide." }
            field = value
        }

    init {
        this.firstName = firstName
        this.lastName = lastName
    }

    override fun toString(): String {
        return "Requete_ADD_PATIENT(firstName='$firstName', lastName='$lastName')"
    }
}
