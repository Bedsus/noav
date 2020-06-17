package com.example.demo.module.position

import tornadofx.*

class PositionModel(position: Position = Position()) : ItemViewModel<Position>(position) {
    val id = bind(Position::idProperty)
    val name = bind(Position::nameProperty)
    val count = bind(Position::countProperty)
}