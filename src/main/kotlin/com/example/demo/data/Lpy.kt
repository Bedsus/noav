package com.example.demo.data

/**
 * Лечебно-профилактические учреждения
 */
class Lpy (
    val id: Int,
    val name: String
) {

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