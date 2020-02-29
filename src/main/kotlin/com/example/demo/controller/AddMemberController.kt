package com.example.demo.controller

import com.example.demo.data.Lpy
import com.example.demo.data.Member
import com.example.demo.data.Position
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller

class AddMemberController : Controller() {

    //TODO Получаем с базы
    private val positionList = listOf(
        Position(1, "Космитолог"),
        Position(2, "Реанимотолог"),
        Position(3, "Кардиолог")
    )

    //TODO Получаем с базы
    private val lpyList = listOf(
            Lpy(1, "ГКП 1"),
            Lpy(2, "ГКП 2"),
            Lpy(3, "ГКП 3")
    )

    //TODO Получаем с базы
    private val freeId: Int
        get() = 1

    val positionObservableList: ObservableList<String>
        get() = FXCollections.observableList(positionList.map { it.name })

    val lpyObservableList: ObservableList<String>
        get() = FXCollections.observableList(lpyList.map { it.name })


    fun saveMember(
        name: String,
        surname: String,
        patronymicName: String,
        email: String,
        snils: String,
        dateEntry: String,
        dateDeparture: String,
        lpy: String,
        position: String
    ) {
        val member = Member(
            id = freeId,
            name = name,
            surname = surname,
            patronymicName = patronymicName,
            email = email,
            snils = snils,
            dateEntry = dateEntry,
            dateDeparture = dateDeparture,
            lpy = lpyList.first { it.name == lpy },
            position = positionList.first { it.name == position }
        )
        member.show()
        //TODO Сохранение члена НОАВ
    }
}