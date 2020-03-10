package com.example.demo.module.member

import com.example.demo.data.Lpy
import com.example.demo.data.Member
import com.example.demo.data.Member.Companion.PERSONAL_CARD_TEXT
import com.example.demo.data.Position
import com.example.demo.util.Shortcut
import com.example.demo.view.Notification.showSaveNotification
import com.example.demo.view.getButton
import com.example.demo.view.getDatePicker
import com.example.demo.view.getTextArea
import javafx.scene.control.TextInputControl
import tornadofx.*

class PersonalCardMemberView : View() {

    private val controller: PersonalCardMemberController by inject()
    private val model: MemberModel by inject()

    override val root = vbox {
        title = PERSONAL_CARD_TEXT
        hbox {
            form {
                fieldset {
                    field(Member.ID_TEXT) { text().bind(model.id) }
                    field(Member.SURNAME_TEXT) { textfield(model.surname).valid() }
                    field(Member.NAME_TEXT) { textfield(model.name).valid() }
                    field(Member.PATRONYMIC_TEXT) { textfield(model.patronymicName) }
                    field(Position.POSITION_TEXT) {
                        combobox<Position>(model.position, values = controller.positionObservableList) {
                            cellFormat { text = it.name }
                            validator {
                                if (it == null || it.name == "") error("Это поле является обязательным") else null
                            }
                        }
                    }
                    field(Lpy.LPY_TEXT) {
                        combobox<Lpy>(model.lpy, values = controller.lpyObservableList) {
                            cellFormat { text = it.name }
                            validator {
                                if (it == null || it.name == "") error("Это поле является обязательным") else null
                            }
                        }
                    }
                    field(Member.ENTRANCE_FEE_TEXT) { textfield(model.entranceFee) }
                    field(Member.YEARLY_FEE_TEXT) { textfield(model.yearlyFee) }
                    field(Member.DATE_ENTRY_TEXT) { getDatePicker().bind(model.dateEntry) }
                    field(Member.DATE_DEPARTURE_TEXT) { getDatePicker().bind(model.dateDeparture) }
                }
            }
            form {
                fieldset {
                    field(Member.SNILS_TEXT) { textfield(model.snils) }
                    field(Member.EMAIL_TEXT) { textfield(model.email) }
                    field(Member.PHONE_TEXT) { textfield(model.phone) }
                    field(Member.PARTICIPATE_EVENT_TEXT) { getTextArea().bind(model.participateEvents) }
                    field(Member.NOTE_TEXT) { getTextArea().bind(model.note) }
                }
            }
        }
        hbox {
            getButton(SAVE) { checkBySaveMember() }.apply { shortcut(Shortcut.SAVE.combo) }
            getButton(CANCEL) { cancel() }.apply { shortcut(Shortcut.EXIT.combo) }
        }
        model.validate(decorateErrors = false)
    }

    private fun TextInputControl.valid() = validator {
        if (it.isNullOrBlank()) error("Это поле является обязательным") else null
    }

    private fun checkBySaveMember() {
        model.commit {
            showSaveNotification()
            println("Сохранено!")
        }
    }

    private fun cancel() {
        close()
    }

    companion object {
        const val SAVE = "Сохранить"
        const val CANCEL = "Отмена"
    }
}