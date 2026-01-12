package model.entity

import java.io.Serializable

class User : Entity, Serializable {

    private var id: Int? = null
    private var login: String? = null
    private var password: String? = null
    private var role: String? = null

    constructor()

    constructor(
        id: Int?,
        login: String?,
        password: String?,
        role: String?
    ) {
        setId(id)
        setLogin(login)
        setPassword(password)
        setRole(role)
    }

    fun getId(): Int? = id
    fun getLogin(): String? = login
    fun getPassword(): String? = password
    fun getRole(): String? = role

    fun setId(id: Int?) {
        this.id = id
    }

    fun setLogin(login: String?) {
        this.login = login?.trim()
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun setRole(role: String?) {
        this.role = role
    }

    override fun toString(): String {
        return "User{" +
                "id=$id, " +
                "login='$login', " +
                "role='$role'" +
                "}"
    }
}
