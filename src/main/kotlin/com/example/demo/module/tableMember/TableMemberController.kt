package com.example.demo.module.tableMember

import com.example.demo.data.Member
import com.example.demo.data.createMember
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*
import javax.json.JsonObject

class TableMemberController : Controller() {

    val api: Rest by inject()

    fun loadDogs(): Dogs = api.get("api/breed/hound/images").one().toModel()

    //TODO Получаем с базы
    val memberList: ObservableList<Member>
        get() = (1..10000).map { createMember(it) }.observable()
}

class Dogs: JsonModel{
    val message: ObservableList<String> = FXCollections.observableArrayList<String>()

    override fun updateModel(json: JsonObject) {
        with(json) {
            message.setAll(getJsonArray("message").map { it.toString() })
        }
    }
}