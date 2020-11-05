package ie.wit.gaastatstracker.controller

import ie.wit.gaastatstracker.models.Match
import ie.wit.gaastatstracker.models.MatchModel
import ie.wit.gaastatstracker.view.LoadMatch
import ie.wit.gaastatstracker.view.NewMatch
import tornadofx.Controller
import tornadofx.asyncItems
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException
import javax.swing.JOptionPane


class CRUDController: Controller() {

    val MainController: MainController by inject()
    val LoadMatch: LoadMatch by inject()
    val NewMatch: NewMatch by inject()
    val model: MatchModel by inject()

    //Search function to Load a chosen match based on the gameID
    fun search(){
        if (LoadMatch.gameID.value >= 1
        ) {
            try {
                val conn: Connection? = MainController.getConnection()
                val s: PreparedStatement
                //Selects data from table based on given gameID
                s = conn!!.prepareStatement("select * from " + MainController.tableName + " where gameID = ?")
                //Sets the gameID in the statement
                s.setInt(1, LoadMatch.gameID.value)
                val rs = s.executeQuery()
                if (rs.next()) {
                    LoadMatch.teamName.set(rs.getString("teamName"))
                    LoadMatch.teamGoals.set(rs.getInt("teamGoals"))
                    LoadMatch.teamPoints.set(rs.getInt("teamPoints"))
                    LoadMatch.oppName.set(rs.getString("oppName"))
                    LoadMatch.oppGoals.set(rs.getInt("oppGoals"))
                    LoadMatch.oppPoints.set(rs.getInt("oppPoints"))
                    s.close()
                } else {
                    println("Can't Load!!")
                }
            } catch (ex: SQLException) {
                ex.printStackTrace()
            }
        }
        else{
            println("Can't Load!!")
        }
    }

    //Deletes a chosen entry in the table based on the gameID entered
    fun delete(){
        if (LoadMatch.gameID.value >= 1
        ) {
            try {
                val conn: Connection? = MainController.getConnection()
                val s: PreparedStatement
                s = conn!!.prepareStatement("delete from " + MainController.tableName + " where gameID = ?")
                s.setInt(1, LoadMatch.gameID.value)
                //Calls function to delete entry on the front end to match the backend
                deleteFromTable(model)
                val count = s.executeUpdate()
                if (count == 0) {
                    JOptionPane.showMessageDialog(null, "Record Not Found \nPlease enter a valid SSN Number")
                }
                //Resets textfields to original state after a delete
                resetFields()
                s.close()
                println("$count rows were deleted")
            } catch (ex: SQLException) {
                ex.printStackTrace()
            }
        }
        else{
            println("Can't delete!!")
        }
    }

    //Deletes the deleted entry from the tableview so frontend matches with the backend
    private fun deleteFromTable(model: MatchModel){
        var id = 0
        //Deletes the entry based on the gameID taken from the model in the delete function
        MainController.listOfMatches.forEachIndexed { index, match ->
            if(match.gameID == model.gameID.value && index != -1){
                id = index
            }
        }
        MainController.listOfMatches.removeAt(id)
    }

    //Adds a match to the database and updates the tableview
    fun add(){
        //Will only add if the game ID is greater than 0 and both team name textfields have been filled
        if (LoadMatch.gameID.value >= 1 && LoadMatch.teamName.value != "" && LoadMatch.oppName.value != ""
        ) {
            try {
                val conn: Connection? = MainController.getConnection()
                val s: PreparedStatement
                s = conn!!.prepareStatement("INSERT INTO " + MainController.tableName + " VALUES(?,?,?,?,?,?,?)")
                s.setInt(1, NewMatch.gameID.value)
                s.setString(2, NewMatch.teamName.value)
                s.setInt(3, NewMatch.teamGoals.value)
                s.setInt(4, NewMatch.teamPoints.value)
                s.setString(5, NewMatch.oppName.value)
                s.setInt(6, NewMatch.oppGoals.value)
                s.setInt(7, NewMatch.oppPoints.value)
                val count = s.executeUpdate()
                s.close()
                //creates a variable match with the new data
                val match = Match(
                    model.gameID.value,
                    model.teamName.value,
                    model.teamGoals.value,
                    model.teamPoints.value,
                    model.oppName.value,
                    model.oppGoals.value,
                    model.oppPoints.value
                )
                //Calls a create match function which takes in the match variable and updates the listOfMatches and tableview
                createMatch(match)
                println("$count rows were inserted")
            } catch (ex: SQLException) {
                JOptionPane.showMessageDialog(null, "No Record Updated \nPlease enter a valid SSN Number")
                ex.printStackTrace()
            }
        }
        else{
            println("Can't add!!")
        }
    }

    //Adds the new match to the list of matches which will update the tableview
    fun createMatch(Match:Match){
        MainController.listOfMatches.add(Match)
    }

    //Updates the database which new values and updates listOfMatches to update the tableview to match backend
    fun update(){
        //Only updates if the game ID is not 0 and the team names are not empty
            if (LoadMatch.gameID.value >= 1 && LoadMatch.teamName.value != "" && LoadMatch.oppName.value != ""
            ) {
                try {
                val conn: Connection? = MainController.getConnection()
                val s: PreparedStatement
                s =
                    conn!!.prepareStatement("update " + MainController.tableName + " set gameID = ?, teamName = ?, teamGoals = ?, teamPoints = ?, oppName = ?, oppGoals = ?, oppPoints = ? where gameID = ?")
                s.setInt(1, LoadMatch.gameID.value)
                s.setString(2, LoadMatch.teamName.value)
                s.setInt(3, LoadMatch.teamGoals.value)
                s.setInt(4, LoadMatch.teamPoints.value)
                s.setString(5, LoadMatch.oppName.value)
                s.setInt(6, LoadMatch.oppGoals.value)
                s.setInt(7, LoadMatch.oppPoints.value)
                s.setInt(8, LoadMatch.gameID.value)
                val count = s.executeUpdate()
                println("$count rows were updated")
                s.close()
                    //Syncs the listOfMatches with the new updated mysql database using the original function to pull the data from the database
                MainController.listOfMatches.asyncItems { MainController.matchList() }
            } catch (ex: SQLException) {
                ex.printStackTrace()
            }
        }
        else{
                println("Can't update!!")
            }
    }

    //Function used to reset the text fields when necessary
    fun resetFields(){
        NewMatch.gameID.set(0)
        NewMatch.teamName.set("")
        NewMatch.teamGoals.set(0)
        NewMatch.teamPoints.set(0)
        NewMatch.oppName.set("")
        NewMatch.oppGoals.set(0)
        NewMatch.oppPoints.set(0)
    }
}