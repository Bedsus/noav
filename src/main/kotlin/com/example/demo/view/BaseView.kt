package com.example.demo.view

import javafx.event.EventTarget
import javafx.scene.control.Button
import javafx.scene.control.DatePicker
import javafx.util.converter.LocalDateStringConverter
import tornadofx.*
import java.time.format.DateTimeFormatter

inline fun EventTarget.getButton(name: String, crossinline onClick: () -> Unit): Button {
    return button(name) {
        style { fontSize = 15.px }
        action { onClick() }
    }
}

fun EventTarget.getDatePicker(): DatePicker {
    return datepicker {
        val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        converter = LocalDateStringConverter(dateFormatter, dateFormatter)
    }
}

const val DATE_FORMAT = "dd-MM-yyyy"