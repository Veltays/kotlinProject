package model.entity

import java.io.Serializable

class Specialty : Entity, Serializable {

    private var id: Int? = null
    private var name: String? = null

    constructor()

    constructor(
        id: Int?,
        name: String?
    ) {
        setId(id)
        setName(name)
    }

    fun getId(): Int? = id
    fun getName(): String? = name

    fun setId(id: Int?) {
        this.id = id
    }

    fun setName(name: String?) {
        this.name = name?.trim()
    }

    override fun toString(): String {
        return "Specialty{" +
                "id=$id, " +
                "name='$name'" +
                "}"
    }
}
