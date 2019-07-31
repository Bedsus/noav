package com.example.demo.view.dialogs

import javafx.application.Platform
import tornadofx.*

class ConfirmExitView: View() {
    override val root = vbox {
        label("Вы действительно хотите выйти?")
        hbox {
            button("Да") {
                action { Platform.exit() }
                shortcut("Enter")
            }
            button("Отмена") {
                action { close() }
                shortcut("Esc")
            }
        }
    }
}