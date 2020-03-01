package com.example.demo.view

import javafx.scene.shape.Rectangle
import javafx.util.Duration
import org.controlsfx.control.Notifications

object Notification {

    fun showShortNotification(title: String, text: String) {
        Notifications.create()
                .title(title)
                .text(text)
                .graphic(Rectangle())
                .hideAfter(Duration.seconds(5.0))
                .show()
    }

    fun showEmptyDataError(nameTextField: String) {
        showShortNotification(INPUT_ERROR, "Поле \"$nameTextField\" не должно быть пустым!")
    }

    fun showNotSelectedDataError(nameTextField: String) {
        showShortNotification(INPUT_ERROR, "Необходимо выбрать \"$nameTextField\"!")
    }

    fun showSaveNotification() {
        showShortNotification(SUCCESS, SAVED)
    }

    private const val INPUT_ERROR = "Ошибка ввода"
    private const val SAVED = "Сохранено"
    private const val SUCCESS = "Успешно"
}