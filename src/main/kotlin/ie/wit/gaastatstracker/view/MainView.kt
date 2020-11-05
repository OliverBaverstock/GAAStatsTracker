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

    val CRUDController: CRUDController by inject()

    override val root: VBox = vbox {

        alignment = Pos.CENTER
        spacing = 10.0

        label(title) {
            addClass(Styles.heading)
        }

        //Button to change view to newMatch and reset fields
        button{
            this.text = "New Match"
            action {
                CRUDController.resetFields()
                replaceWith<NewMatch>()
            }
        }
        //Button to change view to Load match and reset fields
        button{
            this.text = "Load Match"
            action {
               CRUDController.resetFields()
               replaceWith<LoadMatch>()
            }
        }
    }
}
