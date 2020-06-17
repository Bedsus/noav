package com.example.demo.app

import com.example.demo.module.MainView
import tornadofx.*

/**
 * Основной стартовый класс приложения
 */
class Application: App(MainView::class, AppStyle::class)

/**
 * Класс настройки стиля приложения
 */
class AppStyle : Stylesheet()