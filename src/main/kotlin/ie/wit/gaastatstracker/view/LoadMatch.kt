package ie.wit.gaastatstracker.view

import javafx.geometry.Pos
import javafx.scene.layout.VBox
import tornadofx.*

class LoadMatch : View("Load Match") {


    override val root: VBox = vbox {

        spacing = 10.0
        alignment = Pos.CENTER

        hbox {
            label("Game ID")
            gameID = textfield()
            alignment = Pos.TOP_CENTER
            spacing = 10.0
        }
        form{
            alignment = Pos.CENTER
            hbox(10){
                fieldset("Home Team"){
                    vbox{
                        spacing = 10.0
                        field("Team Name"){ teamName = textfield()}
                        field("Team Goals"){teamGoals = textfield()}
                        field("Team Points"){teamPoints = textfield()}
                    }
                }
                fieldset("Opposition Team"){
                    vbox{
                        spacing = 10.0
                        field("Opp Name"){ oppName = textfield()}
                        field("Opp Goals"){oppGoals = textfield()}
                        field("Opp Points"){oppPoints = textfield()}
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

        tabelview(Matches){

        }

    }
}
