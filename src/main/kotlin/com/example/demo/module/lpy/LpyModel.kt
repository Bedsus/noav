package com.example.demo.module.lpy

import tornadofx.*

class LpyModel(lpy: Lpy = Lpy()) : ItemViewModel<Lpy>(lpy) {
    val id = bind(Lpy::idProperty)
    val name = bind(Lpy::nameProperty)
    val count = bind(Lpy::countProperty)
}