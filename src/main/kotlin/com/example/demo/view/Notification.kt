package com.example.demo.view

import javafx.scene.shape.Rectangle
import javafx.util.Duration
import org.controlsfx.control.Notifications

fun showShortNotification(title: String, text: String) {
    Notifications.create()
            .title(title)
            .text(text)
            .graphic(Rectangle())
            .hideAfter(Duration.seconds(5.0))
            .show()
}