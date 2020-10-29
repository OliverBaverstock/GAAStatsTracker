package ie.wit.gaastatstracker.models

import tornadofx.ItemViewModel

class MatchModel: ItemViewModel<Match>() {

    var gameID = bind(Match::gameIDProperty)
    var teamName = bind(Match::teamNameProperty)
    var teamGoals = bind(Match::teamGoalsProperty)
    var teamPoints = bind(Match::teamPointsProperty)
    var oppName = bind(Match::oppNameProperty)
    var oppGoals = bind(Match::oppGoalsProperty)
    var oppPoints = bind(Match::oppPointsProperty)
}