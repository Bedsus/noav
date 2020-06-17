package com.example.demo.module.payment

import com.example.demo.db.DbController
import com.example.demo.db.deletePayment
import com.example.demo.db.getPayments
import com.example.demo.module.confirmAction.ConfirmActionView
import com.example.demo.module.confirmAction.ConfirmViewData
import com.example.demo.module.confirmAction.ConfirmViewDataModel
import com.example.demo.module.member.MemberModel
import com.example.demo.util.DELETE
import com.example.demo.util.Notification.showDeleteNotification
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.TableView
import tornadofx.*

class PaymentView : View() {

    private val memberModel: MemberModel by inject()
    private val paymentModel: PaymentModel by inject()
    private val db: DbController by inject()
    private val confirmViewDataModel: ConfirmViewDataModel by inject()
    private lateinit var paymentTable: TableView<Payment>

    override val root = form {
        title = Payment.PAYMENT_TEXT
        paymentTable = tableview(paymentList) {
            bindSelected(paymentModel)
            readonlyColumn(Payment.NAME_TEXT, Payment::name) { cellFormat { text = it.name } }
            column(Payment.VALUE_TEXT, Payment::value).makeEditable()
            column(Payment.DATE_TEXT, Payment::date).makeEditable()
            contextmenu {
                item(DELETE).action { deletePayment() }
            }
            requestResize()
        }
    }

    private fun deletePayment() {
        val action: () -> Unit = {
            db.deletePayment(memberModel.id.value, paymentModel.item)
            paymentList.clear()
            paymentList.addAll(db.getPayments(memberModel.id.value))
            showDeleteNotification()
        }
        confirmViewDataModel.rebind {
            val data = ConfirmViewData()
            data.questionText = "Вы действительно хотите удалить взнос ${paymentModel.name.value}?"
            data.action = action
            item = data
        }
        openInternalWindow<ConfirmActionView>()
    }

    companion object {
        val paymentList: ObservableList<Payment> = FXCollections.observableArrayList<Payment>()
    }
}