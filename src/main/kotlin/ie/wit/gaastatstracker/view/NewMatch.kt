package ie.wit.gaastatstracker.view


import ie.wit.gaastatstracker.controller.CRUDController
import ie.wit.gaastatstracker.models.MatchModel
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import tornadofx.*


class NewMatch : View("New Match") {
    val model : MatchModel by inject()
    val CRUDController: CRUDController by inject()

    //Initializing variables to be used for text fields
    val gameID = model.gameID
    val teamName = model.teamName
    val teamGoals = model.teamGoals
    val teamPoints = model.teamPoints

    val oppName = model.oppName
    val oppGoals = model.oppGoals
    val oppPoints = model.oppPoints

    //Creates a vertical box
    override val root: VBox = vbox {

        spacing = 10.0
        alignment = Pos.CENTER

        //Creates a horizontal box to hold the GameID textfields
        hbox {
            label("Game ID")
            textfield(gameID)
            alignment = Pos.TOP_CENTER
            spacing = 10.0
        }
        //Creates a form to hold the other textfields
        form{
            alignment = Pos.CENTER
            hbox(10){
                fieldset("Home Team"){
                    vbox{
                        spacing = 10.0
                        field("Team Name"){textfield(teamName)}
                        field("Team Goals"){textfield(teamGoals)}
                        field("Team Points"){textfield(teamPoints)}
                    }
                }
                fieldset("Opposition Team"){
                    vbox{
                        spacing = 10.0
                        field("Opp Name"){textfield(oppName)}
                        field("Opp Goals"){textfield(oppGoals)}
                        field("Opp Points"){textfield(oppPoints)}
                    }
                }
            }
        }
        //Creates a horizontal box to store the buttons
        hbox {
            alignment = Pos.CENTER
            spacing = 30.0

            //Returns to user to the main view
            button{
                this.text = "Return"
                action {
                    replaceWith<MainView>()
                }
            }
            //Saves the entered info using the add function in the CRUDController
            button{
                this.text = "Save Match"
                action {
                    CRUDController.add()
                }
            }
            //Resets all the fields to the original state
            button{
                this.text = "Reset Fields"
                action {
                    println("Reset Fields")
                    CRUDController.resetFields()
                }
            }
        }
    }
}

