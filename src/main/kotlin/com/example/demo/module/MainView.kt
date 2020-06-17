package com.example.demo.module

import com.example.demo.db.*
import com.example.demo.module.confirmAction.ConfirmActionView
import com.example.demo.module.confirmAction.ConfirmViewData
import com.example.demo.module.confirmAction.ConfirmViewDataModel
import com.example.demo.module.lpy.Lpy
import com.example.demo.module.lpy.LpyView
import com.example.demo.module.member.Member
import com.example.demo.module.member.MemberModel
import com.example.demo.module.member.PersonalCardMemberView
import com.example.demo.module.member.PersonalCardMemberView.Companion.ADD_CARD_MEMBER
import com.example.demo.module.member.PersonalCardMemberView.Companion.UPDATE_TAG
import com.example.demo.module.payment.AddingPaymentView
import com.example.demo.module.payment.AddingPaymentView.Companion.ADDING_PAYMENT
import com.example.demo.module.payment.Payment.Companion.PAYMENT_TEXT
import com.example.demo.module.payment.PaymentView
import com.example.demo.module.payment.PaymentView.Companion.paymentList
import com.example.demo.module.position.Position
import com.example.demo.module.position.PositionTableView
import com.example.demo.util.*
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.ComboBox
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import tornadofx.*
import java.time.LocalDate

/**
 * Основное окно прилодения, которое имеет таблицу членов НОАВ и основное меню
 */
class MainView : View(APP_NAME) {

    private val model: MemberModel by inject()
    private val confirmViewDataModel: ConfirmViewDataModel by inject()
    private val positionProperty = SimpleObjectProperty<Position>()
    private val lpyProperty = SimpleObjectProperty<Lpy>()
    private val db: DbController by inject()

    private lateinit var memberTable: TableView<Member>
    private lateinit var searchText: TextField
    private lateinit var lpyComboBox: ComboBox<Lpy>
    private lateinit var positionComboBox: ComboBox<Position>

    private val startUpdate: () -> Unit = { update() }

    init {
        positionProperty.onChange { update() }
        lpyProperty.onChange { update() }
    }

    override val root = vbox {
        form {
            hbox(10) {
                button(ADD_CARD_MEMBER).apply {
                    action {
                        initNewMember()
                        openMemberCard()
                    }
                    style { startMargin = 20.px }
                    shortcut(Shortcut.NEW.combo)
                }
                button(Position.POSITIONS_TEXT).apply {
                    action { openInternalWindow<PositionTableView>() }
                }
                button(Lpy.LPY_TEXT).apply {
                    action { openInternalWindow<LpyView>() }
                    style { prefWidth = 70.px }
                }
            }
        }
        memberTable = tableview(db.getMembers()) {
            column(Member.ID_TEXT, Member::cartProperty)
            readonlyColumn(Member.FIO_TEXT, Member::fio)
            readonlyColumn(Lpy.LPY_TEXT, Member::lpy) { cellFormat { text = it.name } }
            readonlyColumn(Position.POSITION_TEXT, Member::position) { cellFormat { text = it.name } }
            column(Member.DATE_ENTRY_TEXT, Member::dateEntryProperty)
            column(Member.DATE_DEPARTURE_TEXT, Member::dateDepartureProperty)
            onDoubleClick { openMemberCard() }
            bindSelected(model)
            contextmenu {
                item(EDIT).action { openMemberCard() }
                item(PAYMENT_TEXT).action { openPayment() }
                item(ADDING_PAYMENT).action { openAddingPayment() }
                item(DELETE).action { deleteMemberCard() }
            }
        }
        form {
            fieldset {
                field(SEARCH) {
                    searchText = textfield {
                        textProperty().addListener { _, _, _ -> update() }
                    }
                }
                field(FILTERS) {
                    field(Position.POSITION_TEXT) {
                        positionComboBox = combobox<Position>(positionProperty, values = db.getPositions()) {
                            cellFormat { text = it.name }
                        }
                    }
                    field(Lpy.LPY_TEXT) {
                        lpyComboBox = combobox<Lpy>(lpyProperty, values = db.getLpy()) {
                            cellFormat { text = it.name }
                        }
                    }
                }
            }
        }
    }

    private fun update() {
        searchText.text.apply {
            memberTable.items = db.getMembers(
                    cart = toIntOrNull(),
                    surname = this,
                    lpy = lpyComboBox.selectedItem?.id,
                    position = positionComboBox.selectedItem?.id
            )
        }
    }

    private fun openPayment() {
        paymentList.clear()
        paymentList.addAll(db.getPayments(model.id.value))
        openInternalWindow<PaymentView>()
    }

    private fun openAddingPayment() {
        openInternalWindow<AddingPaymentView>()
    }

    private fun initNewMember() {
        model.rebind {
            val newMember = Member()
            newMember.cart = db.getFreeNumberCart()
            newMember.dateEntry = LocalDate.now()
            item = newMember
        }
    }

    private fun openMemberCard() {
        openInternalWindow<PersonalCardMemberView>(params = mapOf(Pair(UPDATE_TAG, startUpdate)))
    }

    private fun deleteMemberCard() {
        val action: () -> Unit = {
            db.deleteMember(model.id.value)
            update()
            Notification.showDeleteNotification()
        }
        confirmViewDataModel.rebind {
            val data = ConfirmViewData()
            data.questionText = "Вы действительно хотите удалить карточку\n${model.item.fio}?"
            data.action = action
            item = data
        }
        openInternalWindow<ConfirmActionView>()
    }
}