package com.example.demo.module.payment

import com.example.demo.util.Shortcut.Companion.ADD
import com.example.demo.util.Shortcut.Companion.DELETE
import tornadofx.*

class PaymentView : View() {

    private val controller: PaymentController by inject()

    override val root = form {
        title = Payment.PAYMENT_TEXT
        tableview<PaymentModel> {
            items = controller.positionObservableList
            isEditable = true
            column(Payment.NAME_TEXT, PaymentModel::name).makeEditable()
            column(Payment.VALUE_TEXT, PaymentModel::value).makeEditable()
            column(Payment.DATE_TEXT, PaymentModel::date).makeEditable()
            contextmenu {
                item(ADD).action {  }
                item(DELETE).action {
                    selectedItem?.let { controller.deleteCategory(it) }
                }
            }
        }
    }
}