package com.example.demo.view.member

import javafx.collections.FXCollections
import javafx.util.converter.LocalDateStringConverter
import tornadofx.*
import java.time.format.DateTimeFormatter

class AddMemberView : View() {

    private val positions = FXCollections.observableArrayList("Космитолог", "Реанимотолог", "Кардиолог")
    private val lpy = FXCollections.observableArrayList("ГКП 1", "ГКП 2", "ГКП 3")

    override val root = vbox {
        title = "Добавление члена НОАВ"
        label("Информация о члене НОАВ # 23") {
            style {
                fontSize = 30.px
            }
        }
       // label("Номер 23")
        hbox {
            form {
                fieldset {
                    field("Фамилия") { textfield() }
                    field("Имя") { textfield() }
                    field("Отчество") { textfield() }
                    field("Должность") { combobox<String> { items = positions } }
                    field("ЛПУ") { combobox<String> { items = lpy } }
                    field("Вступительный взнос") { textfield() }
                    field("Ежегодный взнос") { textfield() }
                    field("Дата вступления") {
                        // TODO сдвигается окно выбора даты
                        datepicker {
                        val pattern = "dd-MM-yyyy"
                        val dateFormatter = DateTimeFormatter.ofPattern(pattern)
                        converter = LocalDateStringConverter(dateFormatter, dateFormatter)
                    } }
                }
            }
            form {
                fieldset {
                    field("СНИЛС") { textfield() }
                    field("E-mail") { textfield() }
                    field("Врач года") { textfield() }
                    field("Примечание") {
                        textarea {
                            prefColumnCount = 2
                            prefRowCount = 3
                        }
                    }
                }
            }
        }
        hbox {
            button("Сохранить") {
                style {
                    fontSize = 15.px
                }
                action {
                    print("Сохранено")
                }
                shortcut("Ctrl+S")
            }
            button("Отмена") {
                style {
                    fontSize = 15.px
                }
                action {
                    close()
                }
                shortcut("Esc")
            }
        }
    }
}