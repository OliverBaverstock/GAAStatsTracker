package ie.wit.gaastatstracker.view

import ie.wit.gaastatstracker.app.Styles
import ie.wit.gaastatstracker.models.Match
import ie.wit.gaastatstracker.models.MatchModel
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import tornadofx.*
import java.net.URI

//var gameID: TextField by singleAssign()
//var teamName: TextField by singleAssign()
//var teamGoals: TextField by singleAssign()
//var teamPoints: TextField by singleAssign()
//
//var oppName: TextField by singleAssign()
//var oppGoals: TextField by singleAssign()
//var oppPoints: TextField by singleAssign()

class NewMatch : View("New Match") {
    val model : MatchModel by inject()
    override val root: VBox = vbox {

        spacing = 10.0
        alignment = Pos.CENTER

        hbox {
            label("Game ID")
            textfield(model.gameID)
            alignment = Pos.TOP_CENTER
            spacing = 10.0
        }
        form{
            alignment = Pos.CENTER
            hbox(10){
                fieldset("Home Team"){
                    vbox{
                        spacing = 10.0
                        field("Team Name"){textfield(model.teamName)}
                        field("Team Goals"){textfield(model.teamGoals)}
                        field("Team Points"){textfield(model.teamPoints)}
                    }
                }
                fieldset("Opposition Team"){
                    vbox{
                        spacing = 10.0
                        field("Opp Name"){textfield(model.oppName)}
                        field("Opp Goals"){textfield(model.oppGoals)}
                        field("Opp Points"){textfield(model.oppPoints)}
                    }
                }
            }
        }
        hbox {
            alignment = Pos.CENTER
            spacing = 30.0

            button{
                this.text = "Return"
                action {
                    replaceWith<MainView>()
                }
            }
            button{
                this.text = "Save Match"
                action {
                    println("Save Match")
                }
            }
            button{
                this.text = "Update Match"
                action {
                    println("Update Match")
                }
            }
        }
    }
}

