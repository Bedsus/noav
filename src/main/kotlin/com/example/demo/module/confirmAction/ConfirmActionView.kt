package com.example.demo.module.confirmAction

import com.example.demo.util.CANCEL
import com.example.demo.util.FONT_SIZE
import com.example.demo.util.Shortcut
import com.example.demo.util.YES
import tornadofx.*

/**
 * Окно подтверждения действия.
 */
class ConfirmActionView: View("Подтверждение удаления") {

    private val confirmViewDataModel: ConfirmViewDataModel by inject()

    override val root = form {
        vbox(10) {
            label().bind(confirmViewDataModel.questionText)
            hbox(10) {
                button(YES) {
                    action {
                        run(confirmViewDataModel.action.value)
                        close()
                    }
                    style {
                        fontSize = FONT_SIZE
                    }
                    shortcut(Shortcut.CONFIRM.combo)
                }
                button(CANCEL) {
                    action { close() }
                    style {
                        fontSize = FONT_SIZE
                    }
                    shortcut(Shortcut.EXIT.combo)
                }
            }
        }
    }
}