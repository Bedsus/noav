package com.example.demo.view.member

import com.example.demo.controller.AddMemberController
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.util.Duration
import javafx.util.converter.LocalDateStringConverter
import org.controlsfx.control.Notifications
import tornadofx.*
import java.time.format.DateTimeFormatter
import javax.swing.Action

class AddMemberView : View() {

    private val controller: AddMemberController by inject()

    private var firstNameField: TextField by singleAssign()
    private var surnameField: TextField by singleAssign()
    private var patronymicNameField: TextField by singleAssign()

    override val root = vbox {
        title = "Добавление члена НОАВ"
        label("Информация о члене НОАВ # 23") { style { fontSize = 30.px } }
        hbox {
            form {
                fieldset {
                    field("Фамилия") { firstNameField = textfield() }
                    field("Имя") { surnameField = textfield() }
                    field("Отчество") { patronymicNameField = textfield() }
                    field("Должность") { combobox<String> { items = controller.positionObservableList } }
                    field("ЛПУ") { combobox<String> { items = controller.lpyObservableList } }
                    field("Вступительный взнос") { textfield() }
                    field("Ежегодный взнос") { textfield() }
                    // TODO сдвигается окно выбора даты
                    field("Дата вступления") { datepicker {
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
                    field("Примечание") { textarea { prefColumnCount = 2; prefRowCount = 3 } }
                }
            }
        }
        hbox {
            button("Сохранить") {
                style { fontSize = 15.px }
                action { saveMember() }
                shortcut("Ctrl+S")
            }
            button("Отмена") {
                style { fontSize = 15.px }
                action { cancel() }
                shortcut("Esc")
            }
        }
    }

    private fun saveMember() {
        print("Сохранено\n")
        if (firstNameField.text.isEmpty()) {
            Notifications.create()
                    .title("Ошибка")
                    .text("Поле Имя не должно быть пустым!")
                    .graphic(Rectangle(0.0, 0.0, Color.GRAY)) // sets node to display
                    .hideAfter(Duration.seconds(5.0))
                    .show()
            return
        }
        controller.saveMember(
            name = firstNameField.text,
            surname = surnameField.text,
            patronymicName = patronymicNameField.text,
            email = "",
            snils = "",
            dateEntry = "",
            dateDeparture = "",
            lpy = "",
            position = ""
        )
    }

    private fun cancel() {
        close()
    }
    internal fun notification(title: String?,
                              text:String?,
                              graphic: Node?,
                              position: Pos = Pos.BOTTOM_RIGHT,
                              hideAfter: Duration = Duration.seconds(5.0),
                              darkStyle: Boolean = false, owner: Any?, vararg action: Action): Notifications {
        val notification = Notifications
                .create()
                .title(title ?: "")
                .text(text ?: "")
                .graphic(graphic)
                .position(position)
                .hideAfter(hideAfter)
                //.action(*action)
        if (owner != null)
            notification.owner(owner)
        if (darkStyle)
            notification.darkStyle()
        return notification
    }

}