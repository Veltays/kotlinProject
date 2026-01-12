package model.entity


import java.io.Serializable

class Doctor : Entity, Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    private var id: Int? = null
    private var specialty_id: Int? = null
    private var lastname: String? = null
    private var firstname: String? = null
    private var user_id: Int? = null

    constructor()

    constructor(
        id: Int?,
        specialty_id: Int?,
        lastname: String?,
        firstname: String?,
        user_id: Int?
    ) {
        setId(id)
        setSpecialty_id(specialty_id)
        setLastname(lastname)
        setFirstname(firstname)
        setUser_id(user_id)
    }

    // GETTERS
    fun getId(): Int? = id
    fun getSpecialty_id(): Int? = specialty_id
    fun getLastname(): String? = lastname
    fun getFirstname(): String? = firstname
    fun getUser_id(): Int? = user_id

    // SETTERS
    fun setId(id: Int?) {
        this.id = id
    }

    fun setSpecialty_id(specialty_id: Int?) {
        this.specialty_id = specialty_id
    }

    fun setLastname(lastname: String?) {
        this.lastname = lastname?.trim()
    }

    fun setFirstname(firstname: String?) {
        this.firstname = firstname?.trim()
    }

    fun setUser_id(user_id: Int?) {
        this.user_id = user_id
    }

    override fun toString(): String {
        return "Doctor{" +
                "id=$id, " +
                "specialty_id=$specialty_id, " +
                "lastname='$lastname', " +
                "firstname='$firstname'" +
                "}"
    }
}
