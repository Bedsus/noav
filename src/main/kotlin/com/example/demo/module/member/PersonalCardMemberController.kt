package com.example.demo.module.member

import com.example.demo.data.Lpy
import com.example.demo.data.Position
import com.example.demo.data.lpyList
import com.example.demo.data.positionList
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*

class PersonalCardMemberController : Controller() {

    //TODO Получаем с базы
    val freeId: Int
        get() = 1

    //TODO Получаем с базы
    val positionObservableList: ObservableList<Position>
        get() = FXCollections.observableList(positionList)

    //TODO Получаем с базы
    val lpyObservableList: ObservableList<Lpy>
        get() = FXCollections.observableList(lpyList)

}