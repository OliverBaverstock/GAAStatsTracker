package ie.wit.gaastatstracker.view

import ie.wit.gaastatstracker.controller.MainController
import ie.wit.gaastatstracker.models.Match
import ie.wit.gaastatstracker.models.MatchModel
import ie.wit.gaastatstracker.view.TableView
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import tornadofx.*
import javax.swing.JTable




class LoadMatch : View("Load Match") {

    //class MainController : Controller()
    val mainController: MainController by inject()
   // val tableView: TableView by inject()

    private val model : MatchModel by inject()

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
        val matchData = mainController.matchList().asObservable()
        tableview(matchData) {
            //items = mainController.matchData1
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

