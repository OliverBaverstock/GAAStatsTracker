package ie.wit.gaastatstracker.view

import ie.wit.gaastatstracker.app.Styles
import ie.wit.gaastatstracker.controller.CRUDController
import ie.wit.gaastatstracker.controller.MainController
import ie.wit.gaastatstracker.models.Match
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import sun.applet.Main
import tornadofx.*

class MainView : View("GAA Stats Tracker") {

    val MainController: MainController by inject()
    val CRUDController: CRUDController by inject()

    override val root: VBox = vbox {

        var x = 0

        alignment = Pos.CENTER
        spacing = 10.0

        label(title) {
            addClass(Styles.heading)
        }

        button{
            this.text = "New Match"
            action {
                CRUDController.resetFields()
                replaceWith<NewMatch>()
            }
        }
        button{
            this.text = "Load Match"
            action {
               CRUDController.resetFields()
               replaceWith<LoadMatch>()
            }
        }
    }
}
