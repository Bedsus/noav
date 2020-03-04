package com.example.demo.module.member

import com.example.demo.data.lpyList
import com.example.demo.data.positionList
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller

class PersonalCardMemberController : Controller() {

    //TODO Получаем с базы
    val freeId: Int
        get() = 1

    //TODO Получаем с базы
    val positionObservableList: ObservableList<String>
        get() = FXCollections.observableList(positionList.map { it.name })

    //TODO Получаем с базы
    val lpyObservableList: ObservableList<String>
        get() = FXCollections.observableList(lpyList.map { it.name })


//    fun saveMember(
//            name: String,
//            surname: String,
//            patronymicName: String,
//            email: String,
//            phone: String,
//            snils: String,
//            dateEntry: String,
//            dateDeparture: String,
//            lpy: String,
//            position: String,
//            yearlyFee: String,
//            entranceFee: String,
//            participateEvents: String,
//            note: String
//    ) {
//        val member = Member(
//                id = freeId,
//                name = name,
//                surname = surname,
//                patronymicName = patronymicName,
//                email = email,
//                phone = phone,
//                snils = snils,
//                dateEntry = dateEntry,
//                dateDeparture = dateDeparture,
//                lpy = lpyList.first { it.name == lpy },
//                position = positionList.first { it.name == position },
//                yearlyFee = yearlyFee,
//                entranceFee = entranceFee,
//                participateEvents = participateEvents,
//                note = note
//        )
//        print(member)
//        //TODO Сохранение члена НОАВ
//    }
}