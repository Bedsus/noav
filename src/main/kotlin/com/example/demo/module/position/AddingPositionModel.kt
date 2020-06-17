package com.example.demo.module.position

import tornadofx.*

class AddingPositionModel(position: AddingPosition = AddingPosition()) : ItemViewModel<AddingPosition>(position) {
    val name = bind(AddingPosition::nameProperty)
}