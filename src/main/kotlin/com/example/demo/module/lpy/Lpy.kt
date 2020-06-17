package com.example.demo.module.lpy

import tornadofx.*
import java.sql.ResultSet
import java.sql.SQLException

/**
 * Лечебно-профилактические учреждения
 */
class Lpy(id: Int = 0, name: String = "", count: Long = 0) {

    var id: Int by property(id)
    fun idProperty() = getProperty(Lpy::id)

    var name: String by property(name)
    fun nameProperty() = getProperty(Lpy::name)

    var count: Long by property(count)
    fun countProperty() = getProperty(Lpy::count)

    fun convertToModel(set: ResultSet): Lpy {
        try {
            id = set.getInt("id")
            name = set.getString("name")
            count = set.getLong("count_member")
        } catch (ex: SQLException) {}
        return this
    }

    override fun toString() = ""
    override fun equals(other: Any?) = (other is Lpy && other.id == id && other.name == name)
    override fun hashCode() = javaClass.hashCode()

    companion object {
        const val LPY_TEXT = "ЛПУ"
        const val LPY_FULL_NAME_TEXT = "Лечебно-профилактические учреждения"

        const val NAME_LPY_TEXT = "Наименование"
        const val COUNT_TEXT = "Количество человек"
    }
}