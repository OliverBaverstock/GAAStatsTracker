package ie.wit.gaastatstracker.models

import javafx.beans.property.Property
import javafx.collections.ObservableList
import tornadofx.*

class MatchModel: ItemViewModel<Match>() {

    var gameID = bind(Match::gameIDProperty)
    var teamName = bind(Match::teamNameProperty)
    var teamGoals = bind(Match::teamGoalsProperty)
    var teamPoints = bind(Match::teamPointsProperty)
    var oppName = bind(Match::oppNameProperty)
    var oppGoals = bind(Match::oppGoalsProperty)
    var oppPoints = bind(Match::oppPointsProperty)

    //On commit if there are changes, the changes will print tp the console
    override fun onCommit(commits: List<Commit>) {
        // The println will only be called if findChanged is not null
        commits.findChanged(gameID)
        commits.findChanged(teamName)?.let { println("Team Name changed from ${it.second} to ${it.first}")}
        commits.findChanged(teamGoals)
        commits.findChanged(oppName)
        commits.findChanged(oppGoals)
        commits.findChanged(oppPoints)

    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }
}

