package com.example.demo.data

import tornadofx.getProperty
import tornadofx.property
import java.time.LocalDate

/**
 * Полная информация о члене НОАВ
 */
class Member {

    var id by property<Int>()
    fun idProperty() = getProperty(Member::id)

    var name by property<String>()
    fun nameProperty() = getProperty(Member::name)

    var surname by property<String>()
    fun surnameProperty() = getProperty(Member::surname)

    var patronymicName by property<String>()
    fun patronymicNameProperty() = getProperty(Member::patronymicName)

    var email by property<String>()
    fun emailProperty() = getProperty(Member::email)

    var phone by property<String>()
    fun phoneProperty() = getProperty(Member::phone)

    var snils by property<String>()
    fun snilsProperty() = getProperty(Member::snils)

    var dateEntry by property<LocalDate>()
    fun dateEntryProperty() = getProperty(Member::dateEntry)

    var dateDeparture by property<LocalDate>()
    fun dateDepartureProperty() = getProperty(Member::dateDeparture)

    var lpy by property<Lpy>()
    fun lpyProperty() = getProperty(Member::lpy)

    var position by property<Position>()
    fun positionProperty() = getProperty(Member::position)

    var yearlyFee by property<String>()
    fun yearlyFeeProperty() = getProperty(Member::yearlyFee)

    var entranceFee by property<String>()
    fun entranceFeeProperty() = getProperty(Member::entranceFee)

    var participateEvents by property<String>()
    fun participateEventsProperty() = getProperty(Member::participateEvents)

    var note by property<String>()
    fun noteProperty() = getProperty(Member::note)

    val fio: String
        get() = "$surname $name $patronymicName"

    val lpyName: String
        get() = lpy.name

    val positionName: String
        get() = position.name

    override fun toString(): String {
        return """
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
        $position
        $lpy
        """.trimIndent()
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