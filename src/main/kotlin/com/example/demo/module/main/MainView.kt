package com.example.demo.module.main

import com.example.demo.module.member.PersonalCardMemberView
import com.example.demo.module.tableMember.Dog
import com.example.demo.module.tableMember.TableMemberController
import com.example.demo.util.Shortcut
import tornadofx.*

class MainView : View() {

    private val controller: TableMemberController by inject()

    override val root = vbox {
        title = "НОАВ 2.0"
        form {
            hbox(10) {
                button("+").apply {
                    action { openInternalWindow<PersonalCardMemberView>() }
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
        listview(controller.loadDogs().message)
//        controller.showFilms {
//            tableView.asyncItems { it }
//        }
//        tableview(controller.memberList) {
//            isEditable = true
//            readonlyColumn(Member.ID_TEXT, Member::id)
//            readonlyColumn(Member.FIO_TEXT, Member::fio)
//            readonlyColumn(Lpy.LPY_TEXT, Member::lpyName)
//            readonlyColumn(Position.POSITION_TEXT, Member::positionName)
//            column(Member.DATE_ENTRY_TEXT, Member::dateEntry)
//            column(Member.DATE_DEPARTURE_TEXT, Member::dateDeparture)
//            onDoubleClick {
//                val id = selectedItem?.id ?: 0
//                openInternalWindow<PersonalCardMemberView>(params = mapOf(PersonalCardMemberView::memberId to id))
//                //find<PersonalCardMemberView>(mapOf(PersonalCardMemberView::memberId to id)).openWindow()
//            }
//        }
    }

}

class HelloWorldStyle : Stylesheet() {
    init {
        root {
            prefWidth = 1200.px
            prefHeight = 800.px
          //  alignment = Pos.CENTER
        }
    }
}