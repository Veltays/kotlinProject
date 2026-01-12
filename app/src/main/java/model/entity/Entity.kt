package model.entity

import java.io.Serializable

interface Entity : Serializable {
    override fun toString(): String
}