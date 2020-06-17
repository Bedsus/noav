package com.example.demo.util

import javafx.event.EventTarget
import javafx.scene.control.Button
import javafx.scene.control.DatePicker
import javafx.scene.control.TextArea
import javafx.scene.control.TextInputControl
import javafx.util.converter.LocalDateStringConverter
import tornadofx.*
import java.time.format.DateTimeFormatter

inline fun EventTarget.getButton(name: String, crossinline onClick: () -> Unit): Button {
    return button(name) {
        style { fontSize = 15.px }
        action { onClick() }
    }
}

fun EventTarget.getTextArea(): TextArea {
    return textarea { prefColumnCount = 2; prefRowCount = 3 }
}

fun EventTarget.getDatePicker(): DatePicker {
    return datepicker {
        val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        converter = LocalDateStringConverter(dateFormatter, dateFormatter)
    }
}

fun TextInputControl.valid() = validator {
    if (it.isNullOrBlank()) error(THIS_FIELD_REQUIRED) else null
}