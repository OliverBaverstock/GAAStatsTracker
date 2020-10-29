package ie.wit.gaastatstracker.models

import tornadofx.ItemViewModel

class AssignModel: ItemViewModel<Match>() {

    val gameID = bind(Match::gameIDProperty)
    val teamName = bind(Match::teamNameProperty)
    val teamGoals = bind(Match::teamGoalsProperty)
    val teamPoints = bind(Match::teamPointsProperty)
    val oppName = bind(Match::oppNameProperty)
    val oppGoals = bind(Match::oppGoalsProperty)
    val oppPoints = bind(Match::oppPointsProperty)
}