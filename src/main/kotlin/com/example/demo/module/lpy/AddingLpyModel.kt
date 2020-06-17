package com.example.demo.module.lpy

import tornadofx.*

class AddingLpyModel(lpy: AddingLpy = AddingLpy()) : ItemViewModel<AddingLpy>(lpy) {
    val name = bind(AddingLpy::nameProperty)
}