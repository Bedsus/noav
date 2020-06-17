package com.example.demo.module.confirmAction

import tornadofx.*

class ConfirmViewDataModel(data: ConfirmViewData = ConfirmViewData()) : ItemViewModel<ConfirmViewData>(data) {
    val questionText = bind(ConfirmViewData::questionProperty)
    val action = bind(ConfirmViewData::actionProperty)
}