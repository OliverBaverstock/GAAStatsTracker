package ie.wit.gaastatstracker.models

import javafx.beans.property.Property
import javafx.collections.ObservableList
import tornadofx.*

class MatchModel: ItemViewModel<Match>() {

    //Binds the variables to the match variables
    var gameID = bind(Match::gameIDProperty)
    var teamName = bind(Match::teamNameProperty)
    var teamGoals = bind(Match::teamGoalsProperty)
    var teamPoints = bind(Match::teamPointsProperty)
    var oppName = bind(Match::oppNameProperty)
    var oppGoals = bind(Match::oppGoalsProperty)
    var oppPoints = bind(Match::oppPointsProperty)

}

