package com.example.demo.data

/**
 * Полная информация о члене НОАВ
 */
class Member (
        val id: Int,
        var name: String,
        var surname: String,
        var patronymicName: String,
        var email: String,
        var phone: String,
        var snils: String,
        var dateEntry: String,
        var dateDeparture: String,
        var lpy: Lpy,
        var position: Position,
        var yearlyFee: String,
        var entranceFee: String,
        var participateEvents: String,
        var note: String
) {

    val fio: String
        get() = "$surname $name $patronymicName"

    val lpyName: String
        get() = lpy.name

    val positionName: String
        get() = position.name

    fun show() {
        print("""
            
         $MEMBER_TEXT:   
        * [$id] - $ID_TEXT
        * [$surname] - $SURNAME_TEXT
        * [$name] - $NAME_TEXT
        * [$patronymicName] - $PATRONYMIC_TEXT
        * [$email] - $EMAIL_TEXT
        * [$phone] - $PHONE_TEXT
        * [$snils] - $SNILS_TEXT
        * [$dateEntry] - $DATE_ENTRY_TEXT
        * [$dateDeparture] - $DATE_DEPARTURE_TEXT
        * [$yearlyFee] - $YEARLY_FEE_TEXT
        * [$entranceFee] - $ENTRANCE_FEE_TEXT
        * [$participateEvents] - $PARTICIPATE_EVENT_TEXT
        * [$note] - $NOTE_TEXT
        """.trimIndent())
        position.show()
        lpy.show()
    }

    companion object {
        const val MEMBER_TEXT = "Член НОАВ"
        const val ID_TEXT = "Номер членской карты"
        const val SURNAME_TEXT = "Фамилия"
        const val NAME_TEXT = "Имя"
        const val PATRONYMIC_TEXT = "Отчество"
        const val FIO_TEXT = "ФИО"
        const val EMAIL_TEXT = "E-Mail"
        const val PHONE_TEXT = "Телефон"
        const val SNILS_TEXT = "СНИЛС"
        const val DATE_ENTRY_TEXT = "Дата вступления"
        const val DATE_DEPARTURE_TEXT = "Дата выхода"
        const val YEARLY_FEE_TEXT = "Ежегодный взнос"
        const val ENTRANCE_FEE_TEXT = "Вступительный взнос"
        const val PARTICIPATE_EVENT_TEXT = "Участие в конкурсах"
        const val NOTE_TEXT = "Примечание"
    }
}