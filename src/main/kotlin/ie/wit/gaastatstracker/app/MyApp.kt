package ie.wit.gaastatstracker.app

import ie.wit.gaastatstracker.controller.MainController
import ie.wit.gaastatstracker.view.MainView
import javafx.stage.Stage
import tornadofx.App

//Starts the stage and sets the width and height
class MyApp: App(MainView::class, Styles::class) {
    var MainController = MainController()
    override fun start(stage: Stage) {
        with(stage) {
            width = 450.0
            height = 700.0
        }
        super.start(stage)
        //Calls connectSQL function from MainController to Connect to the database when the app starts
        MainController.connectSQL()

    }
}