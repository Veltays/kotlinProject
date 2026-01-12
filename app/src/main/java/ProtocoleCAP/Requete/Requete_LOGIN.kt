package ProtocoleCAP.Requete

import protocol.RequeteCAP

class Requete_LOGIN(
    login: String,
    password: String,
    newUser: Boolean
) : RequeteCAP() {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    private var login: String? = null
    private var password: String? = null
    private var NewUser: Boolean = false

    init {
        setLogin(login)
        setPassword(password)
        setNewUser(newUser)
    }

    fun getLogin(): String? = login
    fun getPassword(): String? = password
    fun isNewUser(): Boolean = NewUser

    fun setLogin(login: String?) {
        if (login == null || login.isBlank())
            throw IllegalArgumentException("Le login ne peut pas être vide ou nul.")
        this.login = login
    }

    fun setPassword(password: String?) {
        if (password == null || password.isBlank())
            throw IllegalArgumentException("Le mot de passe ne peut pas être vide ou nul.")
        this.password = password
    }

    fun setNewUser(newUser: Boolean) {
        NewUser = newUser
    }

    override fun toString(): String {
        return "Requete_LOGIN{" +
                "login='$login', " +
                "password='********'}"
    }
}
