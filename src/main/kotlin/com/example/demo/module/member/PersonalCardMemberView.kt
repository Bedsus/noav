package com.example.demo.module.member

import com.example.demo.data.Lpy
import com.example.demo.data.Member
import com.example.demo.data.Position
import com.example.demo.util.Shortcut
import com.example.demo.view.Notification.showEmptyDataError
import com.example.demo.view.Notification.showNotSelectedDataError
import com.example.demo.view.Notification.showSaveNotification
import com.example.demo.view.getButton
import com.example.demo.view.getDatePicker
import com.example.demo.view.getTextArea
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import tornadofx.*

class PersonalCardMemberView : View() {

    private val controller: PersonalCardMemberController by inject()

    //val memberId: Int by param()
    private val memberModel = MemberModel(Member())
    private var positionName: String = ""

    private var nameField: TextField by singleAssign()
    private var surnameField: TextField by singleAssign()
    private var patronymicNameField: TextField by singleAssign()
    private var positionComboBox: ComboBox<String> by singleAssign()
    private var lpyComboBox: ComboBox<String> by singleAssign()

    override val root = vbox {
        title = "$PERSONAL_CARD_TEXT №1"
        hbox {
            form {
                fieldset {
                    field(Member.SURNAME_TEXT) { textfield().bind(memberModel.surname) }
                    field(Member.NAME_TEXT) { textfield().bind(memberModel.name) }
                    field(Member.PATRONYMIC_TEXT) { textfield().bind(memberModel.patronymicName) }
                    field(Position.POSITION_TEXT) { combobox<String> {
                        items = controller.positionObservableList
                       // memberModel.rebindOnChange(selectionModel.selectedItemProperty()) { positionName = it ?: "" }
                    }}
                    field(Lpy.LPY_TEXT) { combobox<String> {
                        items = controller.lpyObservableList

                    }}
                    field(Member.ENTRANCE_FEE_TEXT) { textfield().bind(memberModel.entranceFee) }
                    field(Member.YEARLY_FEE_TEXT) { textfield().bind(memberModel.yearlyFee) }
                    field(Member.DATE_ENTRY_TEXT) { getDatePicker().bind(memberModel.dateEntry) }
                    field(Member.DATE_DEPARTURE_TEXT) { getDatePicker().bind(memberModel.dateDeparture) }
                }
            }
            form {
                fieldset {
                    field(Member.SNILS_TEXT) { textfield().bind(memberModel.snils) }
                    field(Member.EMAIL_TEXT) { textfield().bind(memberModel.email) }
                    field(Member.PHONE_TEXT) { textfield().bind(memberModel.phone) }
                    field(Member.PARTICIPATE_EVENT_TEXT) { getTextArea().bind(memberModel.participateEvents) }
                    field(Member.NOTE_TEXT) { getTextArea().bind(memberModel.note) }
                }
            }
        }
        hbox {
            getButton(SAVE) { checkBySaveMember() }.apply { shortcut(Shortcut.SAVE.combo) }
            getButton(CANCEL) { cancel() }.apply { shortcut(Shortcut.EXIT.combo) }
        }
    }

    private fun checkBySaveMember() {
        when {
            surnameField.text.isEmpty() -> showEmptyDataError(Member.SURNAME_TEXT)
            nameField.text.isEmpty() -> showEmptyDataError(Member.NAME_TEXT)
            patronymicNameField.text.isEmpty() -> showEmptyDataError(Member.PATRONYMIC_TEXT)
            positionComboBox.selectedItem == null -> showNotSelectedDataError(Position.NAME_POSITION_TEXT)
            lpyComboBox.selectedItem == null -> showNotSelectedDataError(Lpy.NAME_LPY_TEXT)
            else -> saveMember()
        }
    }

    private fun saveMember() {
//        controller.saveMember(
//                name = nameField.text,
//                surname = surnameField.text,
//                patronymicName = patronymicNameField.text,
//                email = emailField.text,
//                phone = phoneField.text,
//                snils = snilsField.text,
//                dateEntry = dateEntryDatePicker.editor.text,
//                dateDeparture = dateDepartureDatePicker.editor.text,
//                lpy = lpyComboBox.selectedItem ?: "",
//                position = positionComboBox.selectedItem ?: "",
//                yearlyFee = yearlyFeeField.text,
//                entranceFee = entranceFeeField.text,
//                participateEvents = participateEventsTextArea.text,
//                note = noteTextArea.text
//        )
        showSaveNotification()
    }

    private fun cancel() {
        close()
    }

    companion object {
      //  const val ADD_MEMBER_TEXT = "Добавление члена НОАВ"
        const val PERSONAL_CARD_TEXT = "Личная карточка"
        const val SAVE = "Сохранить"
        const val CANCEL = "Отмена"
    }
}