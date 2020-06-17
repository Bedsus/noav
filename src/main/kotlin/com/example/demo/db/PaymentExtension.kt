package com.example.demo.db

import com.example.demo.module.payment.Payment
import com.example.demo.module.payment.PaymentName
import javafx.collections.ObservableList

/**
 * Получение списка взносов по номеру члена НОАВ [idMember]
 */
fun DbController.getPayments(idMember: Int): ObservableList<Payment> {
    val sql = """
            SELECT
                f.id AS idName,
                f.name AS name,
                df.paydate AS paydate,
                df.amount AS amount
            FROM doctorfees AS df
            JOIN fees AS f ON df.fee = f.id
            WHERE df.doctor = $idMember
            ORDER BY df.paydate, f.name, f.id, df.amount
        """
    showSql(sql)
    return createQuery(sql).toObserverList { Payment().convertToModel(it) }
}

fun DbController.getPaymentsName(): ObservableList<PaymentName> {
    val sql = "SELECT f.id AS idName, f.name AS name FROM fees AS f"
    showSql(sql)
    return createQuery(sql).toObserverList { PaymentName().convertToModel(it) }
}

fun DbController.addPayment(idMember: Int, payment: Payment) {
    payment.apply {
        val sql = "INSERT INTO doctorfees(doctor,fee,paydate,amount) VALUES($idMember,${name.idName},'$date',$value)"
        showSql(sql)
        executeUpdate(sql)
    }
}

fun DbController.deletePayment(idMember: Int, payment: Payment) {
    payment.apply {
        val sql = "DELETE FROM doctorfees AS df WHERE df.doctor = $idMember AND df.fee = ${name.idName} AND df.paydate = '$date'"
        showSql(sql)
        executeUpdate(sql)
    }
}