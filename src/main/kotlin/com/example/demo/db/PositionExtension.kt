package com.example.demo.db

import com.example.demo.module.position.Position
import javafx.collections.ObservableList
import tornadofx.*

/**
 * Получене списка должностей в которых имеются члены НОАВ
 */
fun DbController.getPositions(): ObservableList<Position> {
    val sql ="""
            SELECT
                DISTINCT(p.id) AS id,
                p.name AS name
            FROM posts AS p
            JOIN doctors AS d ON p.id = d.id_post
            ORDER BY p.id, p.name
        """
    showSql(sql)
    val list = mutableListOf(Position()).observable()
    list.addAll(createQuery(sql).toObserverList { Position().convertToModel(it) })
    return list
}

/**
 * Получить полный список должностей
 */
fun DbController.getAllPositions(): ObservableList<Position> {
    val sql = """
        SELECT
            p.id AS id,
            COUNT(d.id) AS count_member,
            p.name AS name
        FROM posts AS p
        LEFT JOIN doctors AS d ON p.id = d.id_post
        GROUP BY p.id, p.name
    """
    showSql(sql)
    return createQuery(sql).toObserverList { Position().convertToModel(it) }
}

/**
 * Получить полный список должностей, где первый элемент списка пустой
 */
fun DbController.getAllPositionsAndOneEmpty(): ObservableList<Position>  {
    val list = mutableListOf(Position()).observable()
    list.addAll(getAllPositions())
    return list
}

/**
 * Изменить наименование должности
 */
fun DbController.editNamePosition(position: Position) {
    position.apply {
        val sql = """
            UPDATE posts AS p SET p.name = '$name'
            WHERE p.id = $id
        """
        showSql(sql)
        executeUpdate(sql)
    }
}

/**
 * Добавить должность
 */
fun DbController.addPosition(namePosition: String) {
    val sql = "INSERT INTO posts(name) VALUES('$namePosition')"
    showSql(sql)
    executeUpdate(sql)
}

/**
 * Удалить должность по ее номеру [id]
 * @return Если у данной должности имеются члены, то удаление невозможно, результат false
 * Если членов нет, то при успешном удалении результат true
 */
fun DbController.deletePosition(id: Int): Boolean {
    val sql = """
         SELECT
            p.id AS id,
            COUNT(d.id) AS count_member,
            p.name AS name
        FROM posts AS p
        LEFT JOIN doctors AS d ON p.id = d.id_post
        WHERE p.id = $id
        GROUP BY p.id, p.name
    """
    showSql(sql)
    val resultSet = createQuery(sql)
    resultSet.next()
    if (resultSet.getLong("count_member") > 0) {
        return false
    }
    val deleteSql = "DELETE FROM posts AS p WHERE p.id = $id"
    showSql(deleteSql)
    executeUpdate(deleteSql)
    return true
}