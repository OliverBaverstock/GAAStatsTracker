package ie.wit.gaastatstracker.view

import ie.wit.gaastatstracker.controller.CRUDController
import ie.wit.gaastatstracker.controller.MainController
import ie.wit.gaastatstracker.models.Match
import ie.wit.gaastatstracker.models.MatchModel
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import tornadofx.*


class LoadMatch : View("Load Match") {

    val mainController: MainController by inject()
    val CRUDController: CRUDController by inject()

    private val model : MatchModel by inject()

    val gameID = model.gameID
    val teamName = model.teamName
    val teamGoals = model.teamGoals
    val teamPoints = model.teamPoints

    val oppName = model.oppName
    val oppGoals = model.oppGoals
    val oppPoints = model.oppPoints

    override val root: VBox = vbox {

        spacing = 10.0
        alignment = Pos.CENTER

        hbox {
            label("Game ID")
            textfield(gameID)
            alignment = Pos.TOP_CENTER
            spacing = 10.0
        }
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
                    println("Save Match")
                    CRUDController.search()
                }
            }
            button{
                this.text = "Delete Match"
                action {
                    println("Update Match")
                }
            }
        }
        val matchData = mainController.matchList().asObservable()
        tableview(matchData) {
            title = "Match"
            column("Game ID", Match::gameID)
            readonlyColumn("Team Name", Match::teamName)
            readonlyColumn("Team Goals", Match::teamGoals)
            readonlyColumn("Team Points", Match::teamPoints)
            readonlyColumn("Opposition Name", Match::oppName)
            readonlyColumn("Opposition Goals", Match::oppGoals)
            readonlyColumn("Opposition Points", Match::oppPoints)
            bindSelected(model)
            smartResize()
        }
        }
    }

