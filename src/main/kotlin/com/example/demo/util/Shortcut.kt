package com.example.demo.util

enum class Shortcut(val combo: String) {
    SAVE("Ctrl+S"),
    NEW("Ctrl+N"),
    CONFIRM("Enter"),
    EXIT("Esc");

    companion object {
        const val DELETE = "Удалить"
        const val ADD = "Добавить"
        const val EDIT = "Изменить"
    }
}