package com.example.demo.module.confirmAction

import tornadofx.*

class ConfirmViewData {
    var questionText: String by property("")
    fun questionProperty() = getProperty(ConfirmViewData::questionText)

    var action: () -> Unit by property()
    fun actionProperty() = getProperty(ConfirmViewData::action)
}