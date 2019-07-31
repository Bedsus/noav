package com.example.demo.view

import javafx.collections.FXCollections
import tornadofx.*

class MainView : View() {
    override val root = borderpane {
        setMinSize(700.0, 500.0)
        label("НОАВ 2.0")
        center = vbox(5) {
            button("Поиск членов НОАВ").apply {
                action {
                    println("Save")
                }
                shortcut("Ctrl+S")
            }
            button("Добавление члена НОАВ").apply {
                action {
                    replaceWith(AddMemberView::class)
                }
            }
            button("Отчеты").apply {

            }
        }
    }
}

class AddMemberView : View(){

    private val positions = FXCollections.observableArrayList("Космитолог", "Реанимотолог","Кардиолог")
    private val lpy = FXCollections.observableArrayList("ГКП 1", "ГКП 2","ГКП 3")

    override val root = vbox {
        title = "Добавление члена НОАВ"
        label("№ 23")
        hbox {
            form {
                fieldset("Информация о члене НОАВ") {
                    field("Фамилия") { textfield() }
                    field("Имя") { textfield() }
                    field("Отчество") { textfield() }
                    field("Должность") {
                        combobox<String> { items = positions }
                    }
                    field("ЛПУ") {
                        combobox<String> { items = lpy }
                    }
                }
                fieldset("Вступление") {
                    field("Вступительный взнос") { textfield() }
                    field("Ежегодный взнос") { textfield() }
                    field("Дата вступления") { datepicker() }
                }
            }
            form {
                fieldset("1") {
                    field("СНИЛС") { textfield() }
                    field("E-mail") { textfield() }
                    field("Врач года") { textfield() }
                    field("Примечание") {
                        textarea{
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
                    replaceWith(MainView::class)
                }
                shortcut("Esc")
            }
        }
    }


}