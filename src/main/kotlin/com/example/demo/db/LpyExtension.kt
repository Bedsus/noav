package com.example.demo.db

import com.example.demo.module.lpy.Lpy
import javafx.collections.ObservableList
import tornadofx.*

/**
 * Получить список ЛПУ в которых имеются члены НОАВ
 */
fun DbController.getLpy(): ObservableList<Lpy> {
    val sql = """
        SELECT
            DISTINCT(l.id) AS id,
            l.name AS name
        FROM lpu AS l
        JOIN doctors AS d ON l.id = d.id_lpu
        ORDER BY l.id, l.name
    """
    showSql(sql)
    val list = mutableListOf(Lpy()).observable()
    list.addAll(createQuery(sql).toObserverList { Lpy().convertToModel(it) })
    return list
}

/**
 * Получить полный список ЛПУ
 */
fun DbController.getAllLpy(): ObservableList<Lpy> {
    val sql = """
       SELECT
            l.id AS id,
            COUNT(d.id) AS count_member,
            l.name AS name
        FROM lpu AS l
        LEFT JOIN doctors AS d ON l.id = d.id_lpu
        GROUP BY l.id, l.name
    """
    showSql(sql)
    return createQuery(sql).toObserverList { Lpy().convertToModel(it) }
}

/**
 * Получить полный список ЛПУ, где первый элемент списка пустой
 */
fun DbController.getAllLpyAndOneEmpty(): ObservableList<Lpy>  {
    val list = mutableListOf(Lpy()).observable()
    list.addAll(getAllLpy())
    return list
}

/**
 * Изменить наименование ЛПУ
 */
fun DbController.editNameLpy(lpy: Lpy) {
    lpy.apply {
        val sql = """
            UPDATE lpu AS l SET l.name = '$name'
            WHERE l.id = $id
        """
        showSql(sql)
        executeUpdate(sql)
    }
}

/**
 * Добавить ЛПУ
 */
fun DbController.addLpy(nameLpy: String) {
    val sql = "INSERT INTO lpu(name) VALUES('$nameLpy')"
    showSql(sql)
    executeUpdate(sql)
}

/**
 * Удалить ЛПУ по его номеру [id]
 * @return Если у данного ЛПУ имеются члены, то удаление невозможно, результат false
 * Если членов нет, то при успешном удалении результат true
 */
fun DbController.deleteLpy(id: Int): Boolean {
    val sql = """
        SELECT
            l.id AS id,
            COUNT(d.id) AS count_member,
            l.name AS name
        FROM lpu AS l
        LEFT JOIN doctors AS d ON l.id = d.id_lpu
        WHERE l.id = $id
        GROUP BY l.id, l.name
    """
    showSql(sql)
    val resultSet = createQuery(sql)
    resultSet.next()
    if (resultSet.getLong("count_member") > 0) {
        return false
    }
    val deleteSql = "DELETE FROM lpu AS l WHERE l.id = $id"
    showSql(deleteSql)
    executeUpdate(deleteSql)
    return true
}

