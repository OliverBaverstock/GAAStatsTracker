package ie.wit.gaastatstracker.view

import tornadofx.*

class TableView : View("My View") {
    val mainController: MainController by inject()
    override val root = TableView<Match> = tableview<Match> {
        items

    }
}
