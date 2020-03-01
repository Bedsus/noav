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
import javafx.scene.control.ComboBox
import javafx.scene.control.DatePicker
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import tornadofx.*

class PersonalCardMemberView : View() {

    private val controller: PersonalCardMemberController by inject()

    val memberId: Int by param()

    private var nameField: TextField by singleAssign()
    private var surnameField: TextField by singleAssign()
    private var patronymicNameField: TextField by singleAssign()
    private var yearlyFeeField: TextField by singleAssign()
    private var entranceFeeField: TextField by singleAssign()
    private var snilsField: TextField by singleAssign()
    private var emailField: TextField by singleAssign()
    private var phoneField: TextField by singleAssign()
    private var noteTextArea: TextArea by singleAssign()
    private var participateEventsTextArea: TextArea by singleAssign()
    private var positionComboBox: ComboBox<String> by singleAssign()
    private var lpyComboBox: ComboBox<String> by singleAssign()
    private var dateEntryDatePicker: DatePicker by singleAssign()
    private var dateDepartureDatePicker: DatePicker by singleAssign()

    override val root = vbox {
        title = "$PERSONAL_CARD_TEXT №$memberId"
        hbox {
            form {
                fieldset {
                    field(Member.SURNAME_TEXT) { surnameField = textfield() }
                    field(Member.NAME_TEXT) { nameField = textfield() }
                    field(Member.PATRONYMIC_TEXT) { patronymicNameField = textfield() }
                    field(Position.POSITION_TEXT) { positionComboBox = combobox { items = controller.positionObservableList } }
                    field(Lpy.LPY_TEXT) { lpyComboBox = combobox { items = controller.lpyObservableList } }
                    field(Member.ENTRANCE_FEE_TEXT) { entranceFeeField = textfield() }
                    field(Member.YEARLY_FEE_TEXT) { yearlyFeeField = textfield() }
                    field(Member.DATE_ENTRY_TEXT) { dateEntryDatePicker = getDatePicker() }
                    field(Member.DATE_DEPARTURE_TEXT) { dateDepartureDatePicker = getDatePicker() }
                }
            }
            form {
                fieldset {
                    field(Member.SNILS_TEXT) { snilsField = textfield() }
                    field(Member.EMAIL_TEXT) { emailField = textfield() }
                    field(Member.PHONE_TEXT) { phoneField = textfield() }
                    field(Member.PARTICIPATE_EVENT_TEXT) { participateEventsTextArea = textarea { prefColumnCount = 2; prefRowCount = 3 }  }
                    field(Member.NOTE_TEXT) { noteTextArea = textarea { prefColumnCount = 2; prefRowCount = 3 } }
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
        controller.saveMember(
                name = nameField.text,
                surname = surnameField.text,
                patronymicName = patronymicNameField.text,
                email = emailField.text,
                phone = phoneField.text,
                snils = snilsField.text,
                dateEntry = dateEntryDatePicker.editor.text,
                dateDeparture = dateDepartureDatePicker.editor.text,
                lpy = lpyComboBox.selectedItem ?: "",
                position = positionComboBox.selectedItem ?: "",
                yearlyFee = yearlyFeeField.text,
                entranceFee = entranceFeeField.text,
                participateEvents = participateEventsTextArea.text,
                note = noteTextArea.text
        )
        showSaveNotification()
    }

    private fun cancel() {
        close()
    }

    companion object {
        const val ADD_MEMBER_TEXT = "Добавление члена НОАВ"
        const val PERSONAL_CARD_TEXT = "Личная карточка"
        const val SAVE = "Сохранить"
        const val CANCEL = "Отмена"
    }
}