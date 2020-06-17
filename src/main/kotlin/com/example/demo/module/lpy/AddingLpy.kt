package com.example.demo.module.lpy

import tornadofx.*

class AddingLpy {
    var name: String by property("")
    fun nameProperty() = getProperty(AddingLpy::name)
}