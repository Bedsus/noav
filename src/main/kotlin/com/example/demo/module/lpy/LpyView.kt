package com.example.demo.module.lpy

import com.example.demo.db.*
import com.example.demo.module.confirmAction.ConfirmActionView
import com.example.demo.module.confirmAction.ConfirmViewData
import com.example.demo.module.confirmAction.ConfirmViewDataModel
import com.example.demo.util.ADD
import com.example.demo.util.DELETE
import com.example.demo.util.Notification.showDeleteNotification
import com.example.demo.util.Notification.showErrorNotification
import com.example.demo.util.Notification.showSaveNotification
import com.example.demo.util.getButton
import com.example.demo.util.valid
import javafx.scene.control.TableView
import tornadofx.*

class LpyView : View() {

    private val lpyModel: LpyModel by inject()
    private val confirmViewDataModel: ConfirmViewDataModel by inject()
    private val addingLpyModel: AddingLpyModel by inject()
    private val db: DbController by inject()
    private lateinit var lpyTable: TableView<Lpy>

    override val root = vbox {
        title = Lpy.LPY_FULL_NAME_TEXT
        lpyTable = tableview(db.getAllLpy()) {
            bindSelected(lpyModel)
            column(Lpy.NAME_LPY_TEXT, Lpy::name).makeEditable()
            column(Lpy.COUNT_TEXT, Lpy::count)
            contextmenu {
                item(DELETE).action { deleteLpy() }
            }
            requestResize()
            enableCellEditing()
            onEditCommit {
                db.editNameLpy(it)
                showSaveNotification()
            }
        }
        form {
            fieldset("Добавить ЛПУ") {
                field {
                    textfield(addingLpyModel.name).valid()
                    getButton(ADD) { saveLpy() }
                }
            }
        }
        addingLpyModel.validate(decorateErrors = false)
    }

    private fun saveLpy() {
        addingLpyModel.commit {
            db.addLpy(addingLpyModel.name.value)
            update()
            showSaveNotification()
        }
    }

    private fun deleteLpy() {
        val action: () -> Unit = {
            if (db.deleteLpy(lpyModel.id.value)) {
                showDeleteNotification()
                update()
            } else {
                showErrorNotification("У данного ЛПУ имеется ${lpyModel.count.value} человек")
            }
        }
        confirmViewDataModel.rebind {
            val data = ConfirmViewData()
            data.questionText = "Вы действительно хотите удалить ЛПУ\n${lpyModel.item.name}?"
            data.action = action
            item = data
        }
        openInternalWindow<ConfirmActionView>()
    }

    private fun update() {
        lpyTable.items = db.getAllLpy()
    }
}