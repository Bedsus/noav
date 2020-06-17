package com.example.demo.module.position

import tornadofx.*

class AddingPosition {
    var name: String by property("")
    fun nameProperty() = getProperty(Position::name)
}