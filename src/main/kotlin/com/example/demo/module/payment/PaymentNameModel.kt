package com.example.demo.module.payment

import tornadofx.*
import java.sql.ResultSet

class PaymentNameModel(payment: PaymentName = PaymentName()) : ItemViewModel<PaymentName>(payment)  {
    val idName = bind(PaymentName::idNameProperty)
    val name = bind(PaymentName::nameProperty)
}

class PaymentName {
    var idName: Int by property(0)
    fun idNameProperty() = getProperty(PaymentName::idName)

    var name: String by property("")
    fun nameProperty() = getProperty(PaymentName::name)

    fun convertToModel(set: ResultSet): PaymentName {
        idName = set.getInt("idName")
        name = set.getString("name")
        return this
    }

    override fun toString() = ""

    companion object {
        const val PAYMENT_NAME = "Тип оплаты"
    }
}