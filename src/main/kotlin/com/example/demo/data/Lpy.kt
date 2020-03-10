package com.example.demo.data

import tornadofx.*

/**
 * Лечебно-профилактические учреждения
 */
class Lpy(id: Int = 0, name: String = "") {

    var id: Int by property(id)
    val name: String by property(name)

    fun show() {
        print("""
            
         $LPY_TEXT:
        * [$id] - $ID_LPY_TEXT
        * [$name] - $NAME_LPY_TEXT
        """.trimIndent())
    }

    companion object {
        const val LPY_TEXT = "ЛПУ"
        const val ID_LPY_TEXT = "Номер ЛПУ"
        const val NAME_LPY_TEXT = "Наименование ЛПУ"
    }
}