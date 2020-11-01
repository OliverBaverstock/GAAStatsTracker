package ie.wit.gaastatstracker.app

import ie.wit.gaastatstracker.controller.MainController
import ie.wit.gaastatstracker.view.MainView
import javafx.stage.Stage
import tornadofx.App

class MyApp: App(MainView::class, Styles::class) {
    var MainController = MainController()
    override fun start(stage: Stage) {
        with(stage) {
            width = 450.0
            height = 700.0
        }
        super.start(stage)
        MainController.connectSQL()

    }
}