package com.example.demo.module.main

import com.example.demo.data.Lpy
import com.example.demo.data.Member
import com.example.demo.data.Position
import com.example.demo.db.DbController
import com.example.demo.module.member.MemberModel
import com.example.demo.module.member.PersonalCardMemberView
import com.example.demo.module.payment.Payment.Companion.PAYMENT_TEXT
import com.example.demo.module.payment.PaymentView
import com.example.demo.util.Shortcut
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.ComboBox
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import tornadofx.*

class MainView : View("НОАВ 2.0") {

    private val model: MemberModel by inject()
    private val positionProperty = SimpleObjectProperty<Position>()
    private val lpyProperty = SimpleObjectProperty<Lpy>()
    private val db: DbController by inject()

    private lateinit var memberTable: TableView<Member>
    private lateinit var searchText: TextField
    private lateinit var lpyComboBox: ComboBox<Lpy>
    private lateinit var positionComboBox: ComboBox<Position>

    init {
        positionProperty.onChange { search() }
        lpyProperty.onChange { search() }
    }

    override val root = vbox {
        form {
            hbox(10) {
                button("+").apply {
                    action {
                        model.rebind { item =  Member() }
                        openMemberCard()
                    }
                    style {
                        prefWidth = 70.px
                        prefHeight = 50.px
                        fontSize = 20.px
                        startMargin = 20.px
                    }
                    shortcut(Shortcut.NEW.combo)
                }
                button("Отчеты").apply {
                    action {
                        // TODO Открываем отчеты
                    }
                    style {
                        prefHeight = 50.px
                        prefWidth = 70.px
                    }
                    shortcut(Shortcut.NEW.combo)
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
                item("Изменить").action { openMemberCard() }
                item(PAYMENT_TEXT).action { openInternalWindow<PaymentView>() }
                item("Удалить").action {
                    selectedItem?.apply { println("Changing Status for $name") }
                }
            }
        }
        form {
            fieldset {
                field("Поиск") {
                    searchText = textfield {
                        textProperty().addListener { _, _, _ -> search() }
                    }
                }
                field("Фильтры") {
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

    private fun search() {
        searchText.text.apply {
            memberTable.items = db.getMembers(
                    cart = toIntOrNull(),
                    surname = this,
                    lpy = lpyComboBox.selectedItem?.id,
                    position = positionComboBox.selectedItem?.id
            )
        }
    }

    private fun openMemberCard() {
        openInternalWindow<PersonalCardMemberView>()
    }
}


class HelloWorldStyle : Stylesheet() {
    init {
        root {
        }
    }
}