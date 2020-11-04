package ie.wit.gaastatstracker.view

import ie.wit.gaastatstracker.controller.CRUDController
import ie.wit.gaastatstracker.controller.MainController
import ie.wit.gaastatstracker.models.Match
import ie.wit.gaastatstracker.models.MatchModel
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import tornadofx.*
import javafx.scene.paint.Color


class LoadMatch : View("Load Match") {

    val MainController: MainController by inject()
    val CRUDController: CRUDController by inject()


    val model : MatchModel by inject()

    var gameID = model.gameID
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
                    saveNewTable()
                }
            }
        }

        tableview<Match> {
            items = MainController.listOfMatches
            title = "Match"
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

    fun saveNewTable(){
        model.commit()
        CRUDController.update()
    }
    }

