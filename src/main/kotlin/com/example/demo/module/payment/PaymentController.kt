package com.example.demo.module.payment

import com.example.demo.data.paymentList
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller

class PaymentController : Controller() {

    val positionObservableList: ObservableList<PaymentModel> by lazy {
        FXCollections.observableList(paymentList)
    }

    fun deleteCategory(model: PaymentModel) {
        positionObservableList.remove(model)
    }
}