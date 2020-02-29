package com.example.demo.data

/**
 * Полная информация о члене НОАВ
 *
 * [id] - уникальный номер
 * [name] - имя
 * [surname] - фамилия
 * [patronymicName] - отчество
 * [email] - почта в сети
 * [snils] - СНИЛС
 * [dateEntry] - дата вступления в организацию
 * [dateDeparture] - дата ухода из организации
 * [lpy] - ЛПУ
 * [position] - Должность сотрудника
 */
class Member (
    val id: Int,
    var name: String,
    var surname: String,
    var patronymicName: String,
    var email: String,
    var snils: String,
    var dateEntry: String,
    var dateDeparture: String,
    var lpy: Lpy,
    var position: Position
) {
    fun show() {
        print("""
        * [$id] - уникальный номер
        * [$name] - имя
        * [$surname] - фамилия
        * [$patronymicName] - отчество
        * [$email] - почта в сети
        * [$snils] - СНИЛС
        * [$dateEntry] - дата вступления в организацию
        * [$dateDeparture] - дата ухода из организации
        * [${lpy.id}] - номер ЛПУ
        * [${lpy.name}] - наименование ЛПУ
        * [${position.id}] - номер должность сотрудника
        * [${position.name}] - наименование должности сотрудника
        """.trimIndent())
    }
}