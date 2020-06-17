package com.example.demo.module.payment

import tornadofx.*

class PaymentModel(payment: Payment = Payment()) : ItemViewModel<Payment>(payment) {
    val name = bind(Payment::nameProperty)
    val amount = bind(Payment::valueProperty)
    val date = bind(Payment::dateProperty)
}