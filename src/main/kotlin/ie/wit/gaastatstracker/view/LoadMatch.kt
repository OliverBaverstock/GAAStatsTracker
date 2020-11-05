package ie.wit.gaastatstracker.view

import ie.wit.gaastatstracker.controller.CRUDController
import ie.wit.gaastatstracker.controller.MainController
import ie.wit.gaastatstracker.models.Match
import ie.wit.gaastatstracker.models.MatchModel
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import tornadofx.*


class LoadMatch : View("Load Match") {

    val MainController: MainController by inject()
    val CRUDController: CRUDController by inject()


    val model : MatchModel by inject()

    //Creates variables to use in text fields and sets them to the model variables
    var gameID = model.gameID
    val teamName = model.teamName
    val teamGoals = model.teamGoals
    val teamPoints = model.teamPoints

    val oppName = model.oppName
    val oppGoals = model.oppGoals
    val oppPoints = model.oppPoints

    //Creates a verticle box
    override val root: VBox = vbox {

        spacing = 10.0
        alignment = Pos.CENTER

        //Creates horizontal box for the gameID textfield
        hbox {
            label("Game ID")
            textfield(gameID)
            alignment = Pos.TOP_CENTER
            spacing = 10.0
        }
        //Creates a form to hold text fields
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
        //Creates a horizontal box to hold the buttons
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
                this.text = "Load Match"
                action {
                    CRUDController.search()
                }
            }
            button{
                this.text = "Delete Match"
                action {
                    CRUDController.delete()
                }
            }
            button{
                this.text = "Update Match"
                action {
                    //saveNewTable()
                    CRUDController.update()
                }
            }
        }

        //Creates the table view
        tableview<Match> {
            //Sets the items to the list of matches created in the Main Controller
            items = MainController.listOfMatches
            title = "Match"
            //Creates the columns and sets them the the corresponding attribute from the mysql db stored in the list of matches
            column("Game ID", Match::gameID)
            column("Team Name", Match::teamName)
            column("Team Goals", Match::teamGoals)
            column("Team Points", Match::teamPoints)
            column("Opposition Name", Match::oppName)
            column("Opposition Goals", Match::oppGoals)
            column("Opposition Points", Match::oppPoints)
            bindSelected(model)
            smartResize()
        }
        }
    }

