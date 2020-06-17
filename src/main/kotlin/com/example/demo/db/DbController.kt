package com.example.demo.db

import com.example.demo.db.DbController.Companion.DATABASE
import com.example.demo.db.DbController.Companion.PASSWORD
import com.example.demo.db.DbController.Companion.USERNAME
import javafx.collections.ObservableList
import tornadofx.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

/**
 * Контроллер подключения к базе данных.
 * Подключение осуществляется по url базы данных [DATABASE], логину [USERNAME] и паролю [PASSWORD].
 */
class DbController : Controller() {

    private val connection: Connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD)

    init {
        beforeShutdown {
            connection.close()
        }
    }

    fun createQuery(sql: String): ResultSet {
        return connection.createStatement().executeQuery(sql)
    }

    fun executeUpdate(sql: String) {
        connection.createStatement().executeUpdate(sql)
    }

    fun showSql(sql: String) {
        print(sql.trimIndent())
        println("")
        println("")
    }

    inline fun <T> ResultSet.toObserverList(convert: (ResultSet) -> T): ObservableList<T> {
        val list = mutableListOf<T>()
        while (next()) {
            list.add(convert(this))
        }
        return list.observable()
    }

    companion object {
        private const val DATABASE = "jdbc:firebirdsql://localhost:3050/C:\\\\noav\\NOAV20190921.FDB?encoding=WIN1251"
        private const val USERNAME = "SYSDBA"
        private const val PASSWORD = "masterkey"
    }
}