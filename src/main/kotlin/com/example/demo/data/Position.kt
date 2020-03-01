package com.example.demo.data

/**
 * Должность сотрудников
 */
class Position (
    val id: Int,
    val name: String
) {

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