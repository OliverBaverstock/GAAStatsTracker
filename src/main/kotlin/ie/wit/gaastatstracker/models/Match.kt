package ie.wit.gaastatstracker.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty

import tornadofx.*

//Creates a class of type Match and initializes the attributes of Match
class Match(
    gameID: Int = 0,
    teamName: String? = null,
    teamGoals: Int = 0,
    teamPoints: Int = 0,
    oppName: String? = null,
    oppGoals: Int = 0,
    oppPoints: Int = 0,){

    val gameIDProperty = SimpleIntegerProperty(gameID)
    var gameID by gameIDProperty

    val teamNameProperty = SimpleStringProperty(this,"teamName", teamName)
    var teamName by teamNameProperty

    val teamGoalsProperty = SimpleIntegerProperty(teamGoals)
    var teamGoals by teamGoalsProperty

    val teamPointsProperty = SimpleIntegerProperty(teamPoints)
    var teamPoints by teamPointsProperty

    val oppNameProperty = SimpleStringProperty(this,"oppName", oppName)
    var oppName by oppNameProperty

    val oppGoalsProperty = SimpleIntegerProperty(oppGoals)
    var oppGoals by oppGoalsProperty

    val oppPointsProperty = SimpleIntegerProperty(oppPoints)
    var oppPoints by oppPointsProperty
}


