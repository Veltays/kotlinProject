package model.entity

import java.io.Serializable
import java.util.Date

class Patient : Entity, Serializable {

    private var id: Int? = null
    private var lastname: String? = null
    private var firstname: String? = null
    private var birth_date: Date? = null

    constructor()

    constructor(
        id: Int?,
        lastname: String?,
        firstname: String?,
        birth_date: Date?
    ) {
        setId(id)
        setLastname(lastname)
        setFirstname(firstname)
        setBirthDate(birth_date)
    }

    fun getId(): Int? = id
    fun getLastname(): String? = lastname
    fun getFirstname(): String? = firstname
    fun getBirthDate(): Date? = birth_date

    fun setId(id: Int?) {
        this.id = id
    }

    fun setLastname(lastname: String?) {
        this.lastname = lastname?.trim()
    }

    fun setFirstname(firstname: String?) {
        this.firstname = firstname?.trim()
    }

    fun setBirthDate(birth_date: Date?) {
        this.birth_date = birth_date
    }

    override fun toString(): String {
        return "Patient{" +
                "id=$id, " +
                "lastname='$lastname', " +
                "firstname='$firstname', " +
                "birth_date=$birth_date" +
                "}"
    }
}
