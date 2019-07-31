package com.example.demo.view

import com.example.demo.view.dialogs.ConfirmExitView
import com.example.demo.view.member.AddMemberView
import javafx.geometry.Pos
import tornadofx.*

class MainView : View() {

    override val root = vbox(5) {
        label("НОАВ 2.0")
        button("Добавление члена НОАВ").apply {
            action { openInternalWindow<AddMemberView>() }
            shortcut("Ctrl+N")
        }
        button("Выход") {
            action { openInternalWindow<ConfirmExitView>() }
            shortcut("Esc")
        }
    }
}

class HelloWorldStyle : Stylesheet() {
    init {
        root {
            prefWidth = 1000.px
            prefHeight = 1000.px
            alignment = Pos.CENTER
        }
    }
}