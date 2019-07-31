package com.example.demo.app

import com.example.demo.view.HelloWorldStyle
import com.example.demo.view.MainView
import tornadofx.*

/**
 * Основной стартовый класс приложения
 *
 * Способы открытия нового окна (вью):
 * [replaceWith] - отобразить вью внутри окна
 * [openWindow] - открыть вью в новом окне
 * [openInternalWindow] // открыть вью как окно в окне
 * [openModal] // открыть вью в новом окне блокируя предыдущее
 */
class MyApp: App(MainView::class, HelloWorldStyle::class) {
   /* override fun start(stage: Stage) { - запретить изменение размера окна (работает только с главным)
        stage.isResizable = false
        super.start(stage)
    }*/
}