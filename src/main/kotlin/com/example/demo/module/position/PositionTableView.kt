package com.example.demo.module.position

import com.example.demo.db.*
import com.example.demo.module.confirmAction.ConfirmActionView
import com.example.demo.module.confirmAction.ConfirmViewData
import com.example.demo.module.confirmAction.ConfirmViewDataModel
import com.example.demo.module.lpy.Lpy
import com.example.demo.util.*
import com.example.demo.util.Notification.showDeleteNotification
import com.example.demo.util.Notification.showErrorNotification
import com.example.demo.util.Notification.showSaveNotification
import javafx.scene.control.TableView
import tornadofx.*

class PositionTableView : View() {

    private val positionModel: PositionModel by inject()
    private val confirmViewDataModel: ConfirmViewDataModel by inject()
    private val addingPositionModel: AddingPositionModel by inject()
    private val db: DbController by inject()
    private lateinit var positionTable: TableView<Position>

    override val root = vbox {
        title = Position.POSITIONS_TEXT
        positionTable = tableview(db.getAllPositions()) {
            bindSelected(positionModel)
            column(Lpy.NAME_LPY_TEXT, Position::name).makeEditable()
            column(Lpy.COUNT_TEXT, Position::count)
            contextmenu {
                item(DELETE).action { deletePosition() }
            }
            requestResize()
            enableCellEditing()
            onEditCommit {
                db.editNamePosition(it)
                showSaveNotification()
            }
        }
        form {
            fieldset("Добавить должность") {
                field {
                    textfield(addingPositionModel.name).valid()
                    getButton(ADD) { savePosition() }
                }
            }
        }
        addingPositionModel.validate(decorateErrors = false)
    }

    private fun savePosition() {
        addingPositionModel.commit {
            db.addPosition(addingPositionModel.name.value)
            update()
            Notification.showSaveNotification()
        }
    }

    private fun deletePosition() {
        val action: () -> Unit = {
            if (db.deletePosition(positionModel.id.value)) {
                showDeleteNotification()
                update()
            } else {
                showErrorNotification("У данной должности имеется ${positionModel.count.value} человек")
            }
        }
        confirmViewDataModel.rebind {
            val data = ConfirmViewData()
            data.questionText = "Вы действительно хотите удалить должность\n${positionModel.item.name}?"
            data.action = action
            item = data
        }
        openInternalWindow<ConfirmActionView>()
    }

    private fun update() {
        positionTable.items = db.getAllPositions()
    }
}