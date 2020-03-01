package com.example.demo.module.tableMember

import com.example.demo.data.Member
import com.example.demo.data.createMember
import javafx.collections.ObservableList
import tornadofx.*

class TableMemberController : Controller() {

    //TODO Получаем с базы
    val memberList: ObservableList<Member>
        get() = (1..10000).map { createMember(it) }.observable()
}