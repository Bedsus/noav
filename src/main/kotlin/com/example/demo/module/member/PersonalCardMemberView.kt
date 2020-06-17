package com.example.demo.module.member

import com.example.demo.db.DbController
import com.example.demo.db.getAllLpyAndOneEmpty
import com.example.demo.db.getAllPositionsAndOneEmpty
import com.example.demo.db.saveMember
import com.example.demo.module.lpy.Lpy
import com.example.demo.module.member.Member.Companion.PERSONAL_CARD_TEXT
import com.example.demo.module.position.Position
import com.example.demo.util.*
import com.example.demo.util.Notification.showSaveNotification
import tornadofx.*

class PersonalCardMemberView : View() {

    private val startUpdate: () -> Unit = params[UPDATE_TAG] as () -> Unit
    private val model: MemberModel by inject()
    private val db: DbController by inject()

    override val root = vbox {
        title = PERSONAL_CARD_TEXT
        hbox {
            form {
                fieldset {
                    field(Member.ID_TEXT) { text().bind(model.cart) }
                    field(Member.SURNAME_TEXT) { textfield(model.surname).valid() }
                    field(Member.NAME_TEXT) { textfield(model.name).valid() }
                    field(Member.PATRONYMIC_TEXT) { textfield(model.patronymicName) }
                    field(Position.POSITION_TEXT) {
                        combobox<Position>(model.position, db.getAllPositionsAndOneEmpty()) {
                            cellFormat { text = it.name }
                            validator {
                                if (it == null || it.name.isEmpty()) error(THIS_FIELD_REQUIRED) else null
                            }
                        }
                    }
                    field(Lpy.LPY_TEXT) {
                        combobox<Lpy>(model.lpy, db.getAllLpyAndOneEmpty()) {
                            cellFormat { text = it.name }
                            validator {
                                if (it == null || it.name.isEmpty()) error(THIS_FIELD_REQUIRED) else null
                            }
                        }
                    }
                    field(Member.DATE_ENTRY_TEXT) { getDatePicker().bind(model.dateEntry) }
                    field(Member.DATE_DEPARTURE_TEXT) { getDatePicker().bind(model.dateDeparture) }
                }
            }
            form {
                fieldset {
                    field(Member.ENTRANCE_FEE_TEXT) { textfield(model.entranceFee) }
                    field(Member.YEARLY_FEE_TEXT) { textfield(model.yearlyFee) }
                    field(Member.EMAIL_TEXT) { textfield(model.email) }
                    field(Member.NOTE_TEXT) { getTextArea().bind(model.note) }
                }
            }
        }
        form {
            hbox(10) {
                getButton(SAVE) { checkBySaveMember() }.apply { shortcut(Shortcut.SAVE.combo) }
                getButton(CANCEL) { cancel() }.apply { shortcut(Shortcut.EXIT.combo) }
            }
        }
        model.validate(decorateErrors = false)
    }

    private fun checkBySaveMember() {
        model.commit {
            db.saveMember(model.item)
            showSaveNotification()
            startUpdate()
            close()
        }
    }

    private fun cancel() {
        close()
    }

    companion object {
        const val UPDATE_TAG = "update_tag"
        const val ADD_CARD_MEMBER = "Добавить личную карточку"
    }
}