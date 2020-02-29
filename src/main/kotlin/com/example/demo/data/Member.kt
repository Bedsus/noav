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
    var phone: String,
    var snils: String,
    var dateEntry: String,
    var dateDeparture: String,
    var lpy: Lpy,
    var position: Position,
    var yearlyFee: String,
    var entranceFee: String,
    var doctorYear: String,
    var note: String
) {
    fun show() {
        print("""
        * [$id] - уникальный номер
        * [$name] - имя
        * [$surname] - фамилия
        * [$patronymicName] - отчество
        * [$email] - почта в сети
        * [$phone] - телефон
        * [$snils] - СНИЛС
        * [$dateEntry] - дата вступления в организацию
        * [$dateDeparture] - дата ухода из организации
        * [$yearlyFee] - ежегодный взнос
        * [$entranceFee] - вступительный взнос
        * [${lpy.id}] - номер ЛПУ
        * [${lpy.name}] - наименование ЛПУ
        * [${position.id}] - номер должность сотрудника
        * [${position.name}] - наименование должности сотрудника
        * [$doctorYear] - Врач года
        * [$note] - Примечание
        """.trimIndent())
    }
}