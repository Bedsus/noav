package com.example.demo.module.main

import com.example.demo.data.Lpy
import com.example.demo.data.Member
import com.example.demo.data.Position
import com.example.demo.module.member.MemberModel
import com.example.demo.module.member.PersonalCardMemberView
import com.example.demo.module.payment.Payment.Companion.PAYMENT_TEXT
import com.example.demo.module.payment.PaymentView
import com.example.demo.module.tableMember.TableMemberController
import com.example.demo.util.Shortcut
import tornadofx.*

class MainView : View("НОАВ 2.0") {

    private val controller: TableMemberController by inject()
    private val model: MemberModel by inject()

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
       // listview(controller.loadDogs().message)
        tableview(controller.memberList) {
            column(Member.ID_TEXT, Member::idProperty)
            readonlyColumn(Member.FIO_TEXT, Member::fio)
            readonlyColumn(Lpy.LPY_TEXT, Member::lpy) { cellFormat { text = it.name } }
            readonlyColumn(Position.POSITION_TEXT, Member::position) { cellFormat { text = it.name } }
            column(Member.DATE_ENTRY_TEXT, Member::dateEntryProperty)
            column(Member.DATE_DEPARTURE_TEXT, Member::dateDepartureProperty)
            onDoubleClick { openMemberCard() }
            bindSelected(model)
           // useMouseDragRowSelect()
            contextmenu {
                item("Изменить").action { openMemberCard() }
                item(PAYMENT_TEXT).action { openInternalWindow<PaymentView>() }
                item("Удалить").action {
                    selectedItem?.apply { println("Changing Status for $name") }
                }
            }
        }
    }

    private fun openMemberCard() {
        openInternalWindow<PersonalCardMemberView>()
    }
}


class HelloWorldStyle : Stylesheet() {
    init {
        root {

//            prefWidth = 1200.px
//            prefHeight = 600.px
        }
    }
}