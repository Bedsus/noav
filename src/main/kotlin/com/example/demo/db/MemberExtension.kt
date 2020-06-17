package com.example.demo.db

import com.example.demo.module.member.Member
import javafx.collections.ObservableList

/**
 * Получение списка членов НОАВ по следушим параметрам:
 * @param cart номер личной карточки
 * @param surname фамилия члена НОАВ
 * @param lpy определенное ЛПУ
 * @param position определенная должность
 */
fun DbController.getMembers(
        cart: Int? = null,
        surname: String? = null,
        lpy: Int? = null,
        position: Int? = null
): ObservableList<Member> {
    val sql = """
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
     """
    showSql(sql)
    return createQuery(sql).toObserverList { Member().convertToModel(it) }
}

/**
 * Метод логики поиска членов НОАВ сследующим приоритетом:
 * 1. По фамилии
 * 2. По номеру личной карточки
 * 3. По ЛПУ и должности
 * 4. По ЛПУ
 * 5. По должности
 * 6. Нет приоритета. В этом слечае загружается весь список
 */
private fun getSearchingWhere(cart: Int?, surname: String?, lpy: Int?, position: Int?) = when {
    surname != null && surname.isNotEmpty() && cart == null -> "WHERE UPPER(d.fam) LIKE UPPER('$surname%\')"
    cart != null -> "WHERE d.member_kart LIKE \'$cart%\'"
    lpy != null && position != null && lpy != 0 && position != 0 -> "WHERE d.id_lpu = $lpy AND d.id_post = $position"
    lpy != null && lpy != 0 -> "WHERE d.id_lpu = $lpy"
    position != null && position != 0 -> "WHERE d.id_post = $position"
    else -> ""
}

/**
 * Удаление личной карточки по ее номеру [id]
 */
fun DbController.deleteMember(id: Int) {
    val sql = "DELETE FROM doctors AS d WHERE d.id = $id"
    showSql(sql)
    executeUpdate(sql)
}

/**
 * Получение номера свободной карточки
 */
fun DbController.getFreeNumberCart(): Int {
    val sql = """
        SELECT d.member_kart AS member_kart
        FROM doctors AS d
        ORDER BY d.member_kart DESC
    """
    showSql(sql)
    val resultSet = createQuery(sql)
    resultSet.next()
    return resultSet.getInt("member_kart") + 1
}

/**
 * Добавление личной карточки
 */
fun DbController.saveMember(member: Member) {
    member.apply {
        val sql = if (id == 0) {
            """ 
                INSERT INTO doctors(id_post,id_lpu,member_kart,im,fam,otch,dat_in,prim,email) 
                VALUES(${position.id},${lpy.id},$cart,'$name','$surname','$patronymicName',$dateEntryText,'$note','$email')
            """
        } else {
            """
                UPDATE doctors AS d SET
                    d.id_post = ${position.id},
                    d.id_lpu = ${lpy.id},
                    d.im = '$name',
                    d.fam = '$surname',
                    d.otch = '$patronymicName',
                    d.dat_in = $dateEntryText,
                    d.dat_out = $dateDepartureText,
                    d.prim = '$note',
                    d.email = '$email'
                WHERE d.id = $id
            """
        }
        showSql(sql)
        executeUpdate(sql)
    }
}