package com.example.demo.db

import com.example.demo.data.Member
import javafx.collections.ObservableList
import tornadofx.*
import java.sql.Connection
import java.sql.DriverManager

class DbController : Controller()  {

    val connection: Connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD)

    init {
        beforeShutdown {
            connection.close()
        }
    }

    fun getMembers(): ObservableList<Member> {
        val resultSet = connection.createStatement()
                .executeQuery("SELECT * FROM old_doctors")
        val list = mutableListOf<Member>()
        while (resultSet.next()) {
            list.add(
                    Member().apply {
                        id = resultSet.getInt("id")
                        name = resultSet.getString("im")
                        surname = resultSet.getString("fam")
                        patronymicName = resultSet.getString("otch")
                    }
            )
        }
        return list.observable()
    }

    companion object {
        private const val DATABASE = "jdbc:firebirdsql://localhost:3050/C:\\\\noav\\NOAV20190921.FDB"
        private const val USERNAME = "SYSDBA"
        private const val PASSWORD = "masterkey"
    }
}