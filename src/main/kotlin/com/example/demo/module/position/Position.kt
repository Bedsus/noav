package com.example.demo.module.position

import tornadofx.*
import java.sql.ResultSet
import java.sql.SQLException

/**
 * Должность сотрудников
 */
class Position(id: Int = 0, name: String = "", count: Long = 0) {

    var id: Int by property(id)
    fun idProperty() = getProperty(Position::id)

    var name: String by property(name)
    fun nameProperty() = getProperty(Position::name)

    var count: Long by property(count)
    fun countProperty() = getProperty(Position::count)

    fun convertToModel(set: ResultSet): Position {
        try {
            id = set.getInt("id")
            name = set.getString("name")
            count = set.getLong("count_member")
        } catch (ex: SQLException) {}
        return this
    }

    override fun equals(other: Any?) = (other is Position && other.id == id && other.name == name)
    override fun hashCode() = javaClass.hashCode()
    override fun toString() = ""

    companion object {
        const val POSITION_TEXT = "Должность"
        const val POSITIONS_TEXT = "Должности"
    }
}