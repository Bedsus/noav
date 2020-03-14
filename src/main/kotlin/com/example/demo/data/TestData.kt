package com.example.demo.data

import com.example.demo.module.payment.Payment
import com.example.demo.module.payment.PaymentModel
import java.time.LocalDate

val positionList = listOf(
        Position(0, ""),
        Position(1, "Космитолог"),
        Position(2, "Реанимотолог"),
        Position(3, "Кардиолог")
)

val lpyList = listOf(
        Lpy(0, ""),
        Lpy(1, "ГКП 1"),
        Lpy(2, "ГКП 2"),
        Lpy(3, "ГКП 3")
)

val paymentList = listOf(
        PaymentModel(Payment(4, "2021", 300)),
        PaymentModel(Payment(3, "2020", 500)),
        PaymentModel(Payment(2, "2019", 400)),
        PaymentModel(Payment(1, "Вступительный", 300))
)

fun createMember(id: Int) =  Member().apply {
    this.id = id
    name = "Станислав"
    surname = "Бубнов"
    patronymicName = "Юрьевич"
    email = "bubnovstas@mail.ru"
    phone = "89232304358"
    snils = "12342574"
    dateEntry = LocalDate.now()
    dateDeparture = LocalDate.now()
    lpy = lpyList[id % 3 + 1]
    position = positionList[id % 3 + 1]
    yearlyFee = (100 * id).toString()
    entranceFee = (200 * id).toString()
    participateEvents = "нет"
    note = ""
}