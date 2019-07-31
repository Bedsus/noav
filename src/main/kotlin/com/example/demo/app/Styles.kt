package com.example.demo.app

import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
    }

    init {
        label and heading {
            padding = box(40.px)
            fontSize = 40.px
            fontWeight = FontWeight.NORMAL
        }
    }
}