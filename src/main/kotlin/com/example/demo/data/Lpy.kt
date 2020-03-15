package com.example.demo.data

import tornadofx.*
import java.sql.ResultSet

/**
 * Лечебно-профилактические учреждения
 */
class Lpy(id: Int = 0, name: String = "") {

    var id: Int by property(id)
    var name: String by property(name)

    fun convertToModel(set: ResultSet): Lpy {
        id = set.getInt("id")
        name = set.getString("name")
        return this
    }

    override fun toString(): String {
        return """
            
         $LPY_TEXT:
        * [$id] - $ID_LPY_TEXT
        * [$name] - $NAME_LPY_TEXT
        """.trimIndent()
    }

    companion object {
        const val LPY_TEXT = "ЛПУ"
        const val ID_LPY_TEXT = "Номер ЛПУ"
        const val NAME_LPY_TEXT = "Наименование ЛПУ"
    }
}