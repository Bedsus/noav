package com.example.demo.module.tableMember

import com.example.demo.data.Member
import com.example.demo.data.createMember
import com.example.demo.network.NetworkService.apiRestService
import com.example.demo.view.Notification
import com.google.gson.annotations.SerializedName
import javafx.collections.ObservableList
import kotlinx.coroutines.*
import tornadofx.*
import kotlin.coroutines.CoroutineContext

class TableMemberController : Controller(), CoroutineScope {

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + exceptionHandler


    inline fun showFilms(crossinline onSuccess: (ObservableList<Dogi>) -> Unit) {
        launch {
            try {
                val dogi = apiRestService
                        .getDogsAsync()
                        .await()
                        .message
                        .map { Dogi(it) }
                        .toMutableList()
                        .observable()
                onSuccess(dogi)
            } catch (ex: Throwable) {
                Notification.showShortNotification("Ошибка загрузки!", ex.localizedMessage)
            }
        }
    }

    //TODO Получаем с базы
    val memberList: ObservableList<Member>
        get() = (1..10000).map { createMember(it) }.observable()
}

data class Dogs(
        @SerializedName("message") val message: List<String>
)

class Dogi(val name: String)