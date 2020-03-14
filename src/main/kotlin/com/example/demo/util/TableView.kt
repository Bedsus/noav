package com.example.demo.util

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ChangeListener
import javafx.scene.control.TableRow
import javafx.scene.control.TableView
import javafx.util.Callback
import tornadofx.onChange

fun <T> TableView<T>.useMouseDragRowSelect() {
    val startEndIndices = SimpleObjectProperty<Pair<Int, Int>>(-1 to -1)
    val preDragSelectedIndices = SimpleObjectProperty<List<Int>>()
    val isDeselect = SimpleBooleanProperty(false)
    val isMouseDragActive = SimpleBooleanProperty(false)

    val changeListener: ChangeListener<Pair<Int, Int>> = ChangeListener { observable, oldValue, newValue ->
        if(newValue.first >= 0 && oldValue.second >= 0) {
            val currentSelection = selectionModel.selectedIndices.asSequence().toList()
            val newEndIndex = newValue.second
            val oldEndIndex = oldValue.second
            val dragStartIndex = newValue.first
            val isRangeGrowing = Math.abs(newEndIndex - dragStartIndex) >= Math.abs(oldEndIndex - dragStartIndex)
            val dragMinIndex = Math.min(dragStartIndex, newEndIndex)
            val dragMaxIndex = Math.max(dragStartIndex, newEndIndex)

            if (isDeselect.value) {
                applyDeselectRangeActions(dragMaxIndex, dragMinIndex, isRangeGrowing, newEndIndex, preDragSelectedIndices)
            } else {
                applySelectionRangeActions(currentSelection, dragMaxIndex, dragMinIndex, isRangeGrowing, newEndIndex, preDragSelectedIndices)
            }
        }
    }

    startEndIndices.addListener(changeListener)

    val existingRowFactory: Callback<TableView<T>, TableRow<T>>? = rowFactory

    setRowFactory {
        val row = existingRowFactory?.call(this@useMouseDragRowSelect)?:TableRow<T>()

        row.apply {
            setupMouseDragRowBehavior(isDeselect, isMouseDragActive, preDragSelectedIndices, startEndIndices, this@useMouseDragRowSelect)
        }
    }
}

private fun <T> TableView<T>.applySelectionRangeActions(currentSelection: List<Int>, dragMaxIndex: Int, dragMinIndex: Int, isRangeGrowing: Boolean, newEndIndex: Int, preDragSelectedIndices: SimpleObjectProperty<List<Int>>) {
    if (isRangeGrowing) {
        selectForRange(dragMaxIndex, dragMinIndex)
    } else {
        deselectIndicesOutsideRangeNotPreviouslySelected(currentSelection, dragMaxIndex, dragMinIndex, preDragSelectedIndices)
    }

    selectionModel.select(newEndIndex)
}

private fun <T> TableView<T>.deselectIndicesOutsideRangeNotPreviouslySelected(currentSelection: List<Int>, dragMaxIndex: Int, dragMinIndex: Int, preDragSelectedIndices: SimpleObjectProperty<List<Int>>) {
    currentSelection.forEach { selectedIndex ->
        if (!preDragSelectedIndices.value.contains(selectedIndex) &&
                (selectedIndex < dragMinIndex || selectedIndex > dragMaxIndex)) {
            selectionModel.clearSelection(selectedIndex)
        }
    }
}

private fun <T> TableView<T>.selectForRange(dragMaxIndex: Int, dragMinIndex: Int) {
    (dragMinIndex..dragMaxIndex).forEach { index ->
        if (!selectionModel.isSelected(index)) selectionModel.select(index)
    }
}

private fun <T> TableView<T>.applyDeselectRangeActions(dragMaxIndex: Int, dragMinIndex: Int, isRangeGrowing: Boolean, newEndIndex: Int, preDragSelectedIndices: SimpleObjectProperty<List<Int>>) {
    if (isRangeGrowing) {
        clearSelectionsForRange(dragMaxIndex, dragMinIndex)
    } else {
        reselectPreviouslySelectedIndicesOutsideRange(dragMaxIndex, dragMinIndex, preDragSelectedIndices)
    }
    selectionModel.clearSelection(newEndIndex)
}

private fun <T> TableView<T>.reselectPreviouslySelectedIndicesOutsideRange(dragMaxIndex: Int, dragMinIndex: Int, preDragSelectedIndices: SimpleObjectProperty<List<Int>>) {
    preDragSelectedIndices.value.forEach { selectedIndex ->
        if (selectedIndex < dragMinIndex || selectedIndex > dragMaxIndex) {
            selectionModel.select(selectedIndex)
        }
    }
}

private fun <T> TableView<T>.clearSelectionsForRange(dragMaxIndex: Int, dragMinIndex: Int) {
    (dragMinIndex..dragMaxIndex).forEach { index ->
        if (selectionModel.isSelected(index)) selectionModel.clearSelection(index)
    }
}

private fun <T> TableRow<T>.setupMouseDragRowBehavior(deselect: SimpleBooleanProperty, isMouseDragActive: SimpleBooleanProperty, preDragSelectedIndices: SimpleObjectProperty<List<Int>>, startEndIndex: SimpleObjectProperty<Pair<Int, Int>>, tableView: TableView<T>) {
    val justSelected = SimpleBooleanProperty(false)

    selectedProperty().onChange {
        if (it && !isMouseDragActive.value) justSelected.set(true)
    }

    setOnMousePressed {
        if (!it.isControlDown) {
            tableView.selectionModel.clearAndSelect(index)
        }
    }

    setOnMouseReleased {
        justSelected.set(false)
    }

    setOnDragDetected {
        if (it.isControlDown && tableView.selectionModel.isSelected(index) && !justSelected.value) deselect.set(true) else deselect.set(false)
        isMouseDragActive.set(true)
        justSelected.set(false)
        startFullDrag()
        preDragSelectedIndices.set(tableView.selectionModel.selectedIndices.asSequence().toList())
        startEndIndex.set(index to index)
    }

    setOnMouseDragEntered {
        startEndIndex.set(startEndIndex.value.first to index)
    }

    setOnMouseDragReleased {
        startEndIndex.set(Pair(-1, -1))
        isMouseDragActive.set(false)
        deselect.set(false)
    }
}