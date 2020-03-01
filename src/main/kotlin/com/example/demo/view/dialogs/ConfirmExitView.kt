package com.example.demo.view.dialogs

import com.example.demo.util.Shortcut
import javafx.application.Platform
import tornadofx.*

class ConfirmExitView: View() {
    override val root = vbox {
        label("Вы действительно хотите выйти?")
        hbox {
            button("Да") {
                action { Platform.exit() }
                shortcut(Shortcut.CONFIRM.combo)
            }
            button("Отмена") {
                action { close() }
                shortcut(Shortcut.EXIT.combo)
            }
        }
    }
}