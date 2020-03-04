package com.example.demo.app

import com.example.demo.module.main.HelloWorldStyle
import com.example.demo.module.main.MainView
import tornadofx.App
import tornadofx.Rest
import tornadofx.replaceWith

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
    val api: Rest by inject()

    init {
        api.baseURI = "https://dog.ceo/"
    }
   /* override fun start(stage: Stage) { - запретить изменение размера окна (работает только с главным)
        stage.isResizable = false
        super.start(stage)
    }*/
}