package com.example.demo.data

import tornadofx.*
import java.sql.ResultSet

/**
 * Должность сотрудников
 */
class Position(id: Int = 0, name: String = "") {

    var id: Int by property(id)
    var name: String by property(name)

    fun convertToModel(set: ResultSet): Position {
        id = set.getInt("id")
        name = set.getString("name")
        return this
    }

    override fun toString(): String {
        return """
            
         $POSITION_TEXT:
        * [$id] - $ID_POSITION_TEXT
        * [$name] - $NAME_POSITION_TEXT
        """.trimIndent()
    }

    companion object {
        const val POSITION_TEXT = "Должность"
        const val ID_POSITION_TEXT = "Номер должности"
        const val NAME_POSITION_TEXT = "Наименование должности"
    }
}