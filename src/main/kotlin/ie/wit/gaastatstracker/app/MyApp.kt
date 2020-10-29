package ie.wit.gaastatstracker.app

import ie.wit.gaastatstracker.controller.MainController
import ie.wit.gaastatstracker.models.Match
import ie.wit.gaastatstracker.view.MainView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.asObservable
import java.util.*

class MyApp: App(MainView::class, Styles::class) {
    var MainController = MainController()
    override fun start(stage: Stage) {
        with(stage) {
            width = 450.0
            height = 700.0
        }
        super.start(stage)
        MainController.connectSQL()
       // println(MainController.matchList().asObservable())
       // println(MainController.matchData.forEach(System.out::print))
        for(i in MainController.matchData){
            println(i.oppNameProperty)
        }
    }
}