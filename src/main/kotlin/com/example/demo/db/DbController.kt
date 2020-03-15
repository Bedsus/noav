package com.example.demo.db

import com.example.demo.data.Lpy
import com.example.demo.data.Member
import com.example.demo.data.Position
import javafx.collections.ObservableList
import tornadofx.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class DbController : Controller()  {

    private val connection: Connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD)

    init {
        beforeShutdown {
            connection.close()
        }
    }

    private fun createQuery(sql: String): ResultSet {
        return connection.createStatement()
                .executeQuery(sql)
    }

    fun getMembers(
        cart: Int? = null,
        surname: String? = null,
        lpy: Int? = null,
        position: Int? = null
    ): ObservableList<Member> {
        return createQuery("""
            SELECT 
                d.id AS id,
                d.member_kart AS cart,
                d.im AS name,
                d.fam AS surname,
                d.otch AS patronymicName,
                d.dat_in AS dateEntry,
                d.dat_out AS dateDeparture,
                d.prim AS note,
                d.email AS email,
                p.id AS idPosition,
                p.name AS namePosition,
                l.id AS idLpu,
                l.name AS nameLpu
            FROM doctors AS d
            JOIN posts AS p ON d.id_post = p.id 
            JOIN lpu AS l ON d.id_lpu = l.id 
            ${getSearchingWhere(cart, surname, lpy, position)}
         """).toObserverList { Member().convertToModel(it) }
    }

    private fun getSearchingWhere(
        cart: Int?,
        surname: String?,
        lpy: Int?,
        position: Int?
    ): String {
        return when {
            surname != null && surname.isNotEmpty() && cart == null -> "WHERE  UPPER(d.fam) LIKE UPPER('$surname%\')"
            cart != null -> "WHERE d.member_kart LIKE \'$cart%\'"
            lpy != null && position != null && lpy != 0 && position != 0 -> "WHERE d.id_lpu = $lpy AND d.id_post = $position"
            lpy != null && lpy != 0 -> "WHERE d.id_lpu = $lpy"
            position != null && position != 0 -> "WHERE d.id_post = $position"
            else -> ""
        }
    }

    fun getPositions(): ObservableList<Position> {
        val list = mutableListOf(Position()).observable()
        list.addAll(
            createQuery("""
                SELECT
                    DISTINCT(p.id) AS id,
                    p.name AS name
                FROM posts AS p
                JOIN doctors AS d ON p.id = d.id_lpu
                ORDER BY p.id, p.name
            """.trimMargin()).toObserverList { Position().convertToModel(it) }
        )
        return list
    }

    fun getLpy(): ObservableList<Lpy> {
        val list = mutableListOf(Lpy()).observable()
        list.addAll(
            createQuery("""
                SELECT
                    DISTINCT(l.id) AS id,
                    l.name AS name
                FROM lpu AS l
                JOIN doctors AS d ON l.id = d.id_lpu
                ORDER BY l.id, l.name
            """.trimIndent()).toObserverList { Lpy().convertToModel(it) }
        )
        return list
    }

    companion object {

        inline fun <T> ResultSet.toObserverList(convert: (ResultSet) -> T): ObservableList<T> {
            val list = mutableListOf<T>()
            while (next()) {
                list.add(convert(this))
            }
            return list.observable()
        }

        private const val DATABASE = "jdbc:firebirdsql://localhost:3050/C:\\\\noav\\NOAV20190921.FDB"
        private const val USERNAME = "SYSDBA"
        private const val PASSWORD = "masterkey"
    }
}