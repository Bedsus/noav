package com.example.demo.data

import tornadofx.*

/**
 * Должность сотрудников
 */
class Position(id: Int = 0, name: String = "") {

    var id: Int by property(id)
    var name: String by property(name)

    fun show() {
        print("""
            
         $POSITION_TEXT:
        * [$id] - $ID_POSITION_TEXT
        * [$name] - $NAME_POSITION_TEXT
        """.trimIndent())
    }

    companion object {
        const val POSITION_TEXT = "Должность"
        const val ID_POSITION_TEXT = "Номер должности"
        const val NAME_POSITION_TEXT = "Наименование должности"
    }
}