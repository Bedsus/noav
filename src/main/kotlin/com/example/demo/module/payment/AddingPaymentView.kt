package com.example.demo.module.payment

import com.example.demo.db.DbController
import com.example.demo.db.addPayment
import com.example.demo.db.getPaymentsName
import com.example.demo.module.member.MemberModel
import com.example.demo.module.payment.Payment.Companion.DATE_TEXT
import com.example.demo.module.payment.Payment.Companion.VALUE_TEXT
import com.example.demo.module.payment.PaymentName.Companion.PAYMENT_NAME
import com.example.demo.util.*
import com.example.demo.util.Notification.showSaveNotification
import tornadofx.*

class AddingPaymentView : View() {

    private val memberModel: MemberModel by inject()
    private val paymentModel: PaymentModel by inject()
    private val db: DbController by inject()

    override val root = vbox {
        title = ADDING_PAYMENT
        vbox {
            form {
                fieldset {
                    field(PAYMENT_NAME) {
                        combobox<PaymentName>(paymentModel.name, db.getPaymentsName()) {
                            cellFormat { text = it.name }
                            validator {
                                if (it == null || it.name.isEmpty()) error(THIS_FIELD_REQUIRED) else null
                            }
                        }
                    }
                    field(DATE_TEXT) { getDatePicker().bind(paymentModel.date) }
                    field(VALUE_TEXT) { textfield {
                        filterInput { it.controlNewText.isInt() }
                        bind(paymentModel.amount)
                        validator {
                            if (it.isNullOrBlank() || !it.isInt() || it.toInt() == 0) error(THIS_FIELD_REQUIRED) else null
                        }
                    } }
                }
            }
            form {
                hbox(10) {
                    getButton(SAVE) { savePayment() }.apply { shortcut(Shortcut.SAVE.combo) }
                    getButton(CANCEL) { close() }.apply { shortcut(Shortcut.EXIT.combo) }
                }
            }
        }
        paymentModel.validate(decorateErrors = false)
    }

    private fun savePayment() {
        paymentModel.commit {
            db.addPayment(memberModel.id.value, paymentModel.item)
            showSaveNotification()
            close()
        }
    }

    companion object {
        const val ADDING_PAYMENT = "Добавление оплаты"
    }

}