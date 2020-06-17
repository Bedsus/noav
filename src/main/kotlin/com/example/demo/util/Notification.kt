package com.example.demo.util

import javafx.scene.shape.Rectangle
import javafx.util.Duration
import org.controlsfx.control.Notifications

/**
 * Обьект отвечающий за отображения уведомлений.
 * Поддерживается только с версией java >= 10
 */
object Notification {

    private fun showNotification(title: String, text: String, duration: Double) {
        Notifications.create()
                .title(title)
                .text(text)
                .graphic(Rectangle())
                .hideAfter(Duration.seconds(duration))
                .show()
    }

    fun showSaveNotification() {
        showNotification(SUCCESS, SAVED, SHORT_DURATION)
    }

    fun showDeleteNotification() {
        showNotification(SUCCESS, DELETED, SHORT_DURATION)
    }

    fun showErrorNotification(description: String) {
        showNotification(ERROR, description, LONG_DURATION)
    }

    private const val SHORT_DURATION = 5.0
    private const val LONG_DURATION = 10.0
}