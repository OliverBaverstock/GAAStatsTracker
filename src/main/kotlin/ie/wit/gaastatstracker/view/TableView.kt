package ie.wit.gaastatstracker.view

import ie.wit.gaastatstracker.controller.MainController
import ie.wit.gaastatstracker.models.MatchModel
import ie.wit.gaastatstracker.models.Match
import tornadofx.*

class TableView : View("My View") {
    val mainController: MainController by inject()
    val model: MatchModel by inject()

    override val root = tableview<Match> {
            items = mainController.matchData
            readonlyColumn("Game ID", Match::gameIDProperty)
            readonlyColumn("Team Name", Match::teamName)
            readonlyColumn("Team Goals", Match::teamGoalsProperty)
            readonlyColumn("Team Points", Match::teamPoints)
            readonlyColumn("Opposition Name", Match::oppName)
            readonlyColumn("Opposition Goals", Match::oppGoals)
            readonlyColumn("Opposition Points", Match::oppPoints)
            bindSelected(model)
            smartResize()
    }
}
