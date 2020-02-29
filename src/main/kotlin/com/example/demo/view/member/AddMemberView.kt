package com.example.demo.view.member

import com.example.demo.controller.AddMemberController
import com.example.demo.view.showShortNotification
import javafx.scene.control.ComboBox
import javafx.scene.control.DatePicker
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.util.converter.LocalDateStringConverter
import tornadofx.*
import java.time.format.DateTimeFormatter

class AddMemberView : View() {

    private val controller: AddMemberController by inject()

    private var firstNameField: TextField by singleAssign()
    private var surnameField: TextField by singleAssign()
    private var patronymicNameField: TextField by singleAssign()
    private var yearlyFeeField: TextField by singleAssign()
    private var entranceFeeField: TextField by singleAssign()
    private var snilsField: TextField by singleAssign()
    private var emailField: TextField by singleAssign()
    private var phoneField: TextField by singleAssign()
    private var noteTextArea: TextArea by singleAssign()
    private var doctorYearTextArea: TextArea by singleAssign()
    private var positionComboBox: ComboBox<String> by singleAssign()
    private var lpyComboBox: ComboBox<String> by singleAssign()
    private var dateEntryDatePicker: DatePicker by singleAssign()

    override val root = vbox {
        title = "Добавление члена НОАВ"
        label("Информация о члене НОАВ # 23") { style { fontSize = 30.px } }
        hbox {
            form {
                fieldset {
                    field("Фамилия") { surnameField = textfield() }
                    field("Имя") { firstNameField = textfield() }
                    field("Отчество") { patronymicNameField = textfield() }
                    field("Должность") { positionComboBox = combobox { items = controller.positionObservableList } }
                    field("ЛПУ") { lpyComboBox = combobox { items = controller.lpyObservableList } }
                    field("Вступительный взнос") { entranceFeeField = textfield() }
                    field("Ежегодный взнос") { yearlyFeeField = textfield() }
                    field("Дата вступления") { dateEntryDatePicker = datepicker {   // TODO сдвигается окно выбора даты
                        val pattern = "dd-MM-yyyy"
                        val dateFormatter = DateTimeFormatter.ofPattern(pattern)
                        converter = LocalDateStringConverter(dateFormatter, dateFormatter)
                    } }
                }
            }
            form {
                fieldset {
                    field("СНИЛС") { snilsField = textfield() }
                    field("E-mail") { emailField = textfield() }
                    field("Телефон") { phoneField = textfield() }
                    field("Врач года") { doctorYearTextArea = textarea { prefColumnCount = 2; prefRowCount = 3 }  }
                    field("Примечание") { noteTextArea = textarea { prefColumnCount = 2; prefRowCount = 3 } }
                }
            }
        }
        hbox {
            button("Сохранить") {
                style { fontSize = 15.px }
                action { checkBySaveMember() }
                shortcut("Ctrl+S")
            }
            button("Отмена") {
                style { fontSize = 15.px }
                action { cancel() }
                shortcut("Esc")
            }
        }
    }

    private fun checkBySaveMember() {
        when {
            firstNameField.text.isEmpty() -> showShortNotification(INPUT_ERROR, NAME_INPUT_ERROR)
            surnameField.text.isEmpty() -> showShortNotification(INPUT_ERROR, SURNAME_INPUT_ERROR)
            patronymicNameField.text.isEmpty() -> showShortNotification(INPUT_ERROR, PATRONYMIC_INPUT_ERROR)
            positionComboBox.selectedItem == null -> showShortNotification(INPUT_ERROR, POSITION_SELECT_ERROR)
            lpyComboBox.selectedItem == null -> showShortNotification(INPUT_ERROR, LPY_SELECT_ERROR)
            else -> saveMember()
        }
    }

    private fun saveMember() {
        controller.saveMember(
                name = firstNameField.text,
                surname = surnameField.text,
                patronymicName = patronymicNameField.text,
                email = emailField.text,
                phone = phoneField.text,
                snils = snilsField.text,
                dateEntry = dateEntryDatePicker.editor.text,
                dateDeparture = "",
                lpy = lpyComboBox.selectedItem ?: "",
                position = positionComboBox.selectedItem ?: "",
                yearlyFee = yearlyFeeField.text,
                entranceFee = entranceFeeField.text,
                doctorYear = doctorYearTextArea.text,
                note = noteTextArea.text
        )
        showShortNotification(SUCCESS, SAVED)
    }

    private fun cancel() {
        close()
    }

    companion object {
        const val INPUT_ERROR = "Ошибка ввода"
        const val NAME_INPUT_ERROR = "Поле Имя не должно быть пустым!"
        const val SURNAME_INPUT_ERROR = "Поле Фамилия не должно быть пустым!"
        const val PATRONYMIC_INPUT_ERROR = "Поле Отчество не должно быть пустым!"
        const val POSITION_SELECT_ERROR = "Необходимо выбрать должность!"
        const val LPY_SELECT_ERROR = "Необходимо выбрать ЛПУ!"
        const val SAVED = "Сохранено"
        const val SUCCESS = "Успешно"
    }

}