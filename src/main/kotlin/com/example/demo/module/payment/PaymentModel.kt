package com.example.demo.module.payment

import tornadofx.ItemViewModel

class PaymentModel(payment: Payment = Payment()) : ItemViewModel<Payment>(payment) {
    val id = bind(Payment::idProperty)
    val name = bind(Payment::nameProperty)
    val value = bind(Payment::valueProperty)
    val date = bind(Payment::dateProperty)
}