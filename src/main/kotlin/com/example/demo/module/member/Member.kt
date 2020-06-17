package com.example.demo.module.member

import com.example.demo.module.lpy.Lpy
import com.example.demo.module.position.Position
import tornadofx.*
import java.sql.ResultSet
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Полная информация о члене НОАВ
 */
class Member {

    var id: Int by property(0)
    fun idProperty() = getProperty(Member::id)

    var cart: Int by property(0)
    fun cartProperty() = getProperty(Member::cart)

    var name: String by property("")
    fun nameProperty() = getProperty(Member::name)

    var surname: String by property("")
    fun surnameProperty() = getProperty(Member::surname)

    var patronymicName: String by property("")
    fun patronymicNameProperty() = getProperty(Member::patronymicName)

    var email: String by property("")
    fun emailProperty() = getProperty(Member::email)

    var dateEntry: LocalDate by property<LocalDate>(null)
    fun dateEntryProperty() = getProperty(Member::dateEntry)

    var dateDeparture: LocalDate by property<LocalDate>(null)
    fun dateDepartureProperty() = getProperty(Member::dateDeparture)

    var lpy: Lpy by property(Lpy())
    fun lpyProperty() = getProperty(Member::lpy)

    var position: Position by property(Position())
    fun positionProperty() = getProperty(Member::position)

    var yearlyFee: String by property("")
    fun yearlyFeeProperty() = getProperty(Member::yearlyFee)

    var entranceFee: String by property("")
    fun entranceFeeProperty() = getProperty(Member::entranceFee)

    var note: String by property("")
    fun noteProperty() = getProperty(Member::note)

    val dateEntryText: String?
        get() = if(dateEntry != null) "'$dateEntry'" else null

    val dateDepartureText: String?
        get() = if(dateDeparture != null) "'$dateDeparture'" else null

    val fio: String
        get() = "${surname.toCapital()} ${name.toCapital()} ${patronymicName.toCapital()}"

    override fun toString(): String {
        return """
         $MEMBER_TEXT:   
        * [$id] - $ID_TEXT
        * [$surname] - $SURNAME_TEXT
        * [$name] - $NAME_TEXT
        * [$patronymicName] - $PATRONYMIC_TEXT
        * [$email] - $EMAIL_TEXT
        * [$dateEntry] - $DATE_ENTRY_TEXT
        * [$dateDeparture] - $DATE_DEPARTURE_TEXT
        * [$yearlyFee] - $YEARLY_FEE_TEXT
        * [$entranceFee] - $ENTRANCE_FEE_TEXT
        * [$note] - $NOTE_TEXT
        $position
        $lpy
        """.trimIndent()
    }

    fun convertToModel(set: ResultSet): Member {
        set.apply {
            id = getInt("id")
            cart = getInt("cart")
            name = getString("name")
            surname = getString("surname")
            patronymicName = getString("patronymicName")
            position = Position(
                    id = getInt("idPosition"),
                    name = getString("namePosition")
            )
            lpy = Lpy(
                    id = getInt("idLpu"),
                    name = getString("nameLpu")
            )
            getString("dateEntry")?.let { dateEntry = convertDate(it) }
            getString("dateDeparture")?.let { dateDeparture = convertDate(it) }
            note = getString("note") ?: ""
            email = getString("email") ?: ""
            return this@Member
        }
    }

    companion object {

        fun String.toCapital(): String = toLowerCase().capitalize()

        fun convertDate(date: String): LocalDate {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT))
        }

        private const val DATE_FORMAT = "yyyy-MM-dd"

        const val PERSONAL_CARD_TEXT = "Личная карточка"
        const val MEMBER_TEXT = "Член НОАВ"
        const val ID_TEXT = "Номер членской карты"
        const val SURNAME_TEXT = "Фамилия"
        const val NAME_TEXT = "Имя"
        const val PATRONYMIC_TEXT = "Отчество"
        const val FIO_TEXT = "ФИО"
        const val EMAIL_TEXT = "E-Mail"
        const val DATE_ENTRY_TEXT = "Дата вступления"
        const val DATE_DEPARTURE_TEXT = "Дата выхода"
        const val YEARLY_FEE_TEXT = "Ежегодный взнос"
        const val ENTRANCE_FEE_TEXT = "Вступительный взнос"
        const val NOTE_TEXT = "Примечание"
    }
}