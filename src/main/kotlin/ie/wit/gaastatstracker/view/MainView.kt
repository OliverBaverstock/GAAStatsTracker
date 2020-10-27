package ie.wit.gaastatstracker.view

import ie.wit.gaastatstracker.app.Styles
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import tornadofx.*

class MainView : View("GAA Stats Tracker") {
    override val root: VBox = vbox {

        alignment = Pos.CENTER
        spacing = 10.0

        label(title) {
            addClass(Styles.heading)
        }

        button{
            this.text = "New Match"
            action {
                replaceWith<NewMatch>()
            }
        }
        button{
            this.text = "Load Match"
            action {
               replaceWith<LoadMatch>()
            }
        }
    }
}
