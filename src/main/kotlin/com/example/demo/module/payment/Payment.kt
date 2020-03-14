package com.example.demo.module.payment

import tornadofx.getProperty
import tornadofx.property
import java.time.LocalDate

class Payment(id: Int = 0, name: String = "", value: Long = 0L, date: LocalDate = LocalDate.now()) {

    var id: Int by property(id)
    fun idProperty() = getProperty(Payment::id)

    var name: String by property(name)
    fun nameProperty() = getProperty(Payment::name)

    var value: Long by property(value)
    fun valueProperty() = getProperty(Payment::value)

    var date: LocalDate by property(date)
    fun dateProperty() = getProperty(Payment::date)

    companion object {
        const val PAYMENT_TEXT = "Взносы"
        const val NAME_TEXT = "Взнос"
        const val VALUE_TEXT = "Сумма"
        const val DATE_TEXT = "Дата"
    }
}