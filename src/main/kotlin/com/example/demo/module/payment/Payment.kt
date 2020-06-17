package com.example.demo.module.payment

import com.example.demo.module.member.Member
import tornadofx.*
import java.sql.ResultSet
import java.time.LocalDate

class Payment {

    var name: PaymentName by property(PaymentName())
    fun nameProperty() = getProperty(Payment::name)

    var value: Long by property(0L)
    fun valueProperty() = getProperty(Payment::value)

    var date: LocalDate by property(LocalDate.now())
    fun dateProperty() = getProperty(Payment::date)

    fun convertToModel(set: ResultSet): Payment {
        name.idName = set.getInt("idName")
        name.name = set.getString("name")
        value = set.getLong("amount")
        set.getString("paydate")?.let { date = Member.convertDate(it) }
        return this
    }

    companion object {
        const val PAYMENT_TEXT = "Взносы"
        const val NAME_TEXT = "Взнос"
        const val VALUE_TEXT = "Сумма"
        const val DATE_TEXT = "Дата взноса"
    }
}