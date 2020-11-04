package ie.wit.gaastatstracker.controller

import ie.wit.gaastatstracker.models.Match
import ie.wit.gaastatstracker.models.MatchModel
import ie.wit.gaastatstracker.view.LoadMatch
import ie.wit.gaastatstracker.view.NewMatch
import javafx.beans.property.Property
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import sun.applet.Main
import tornadofx.Controller
import tornadofx.asObservable
import tornadofx.asyncItems
import java.awt.Dialog
import java.awt.Window
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement
import javax.swing.JOptionPane


class CRUDController: Controller() {

    val MainController: MainController by inject()
    val LoadMatch: LoadMatch by inject()
    val NewMatch: NewMatch by inject()
    val model: MatchModel by inject()

    fun search(){
        if (LoadMatch.gameID.value >= 1
        ) {
            try {
                val conn: Connection? = MainController.getConnection()
                val s: PreparedStatement
                s = conn!!.prepareStatement("select * from " + MainController.tableName + " where gameID = ?")
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
                    JOptionPane.showMessageDialog(null, "Record Not Found \nPlease enter a valid SSN Number")
                }
            } catch (ex: SQLException) {
                ex.printStackTrace()
            }
        }
        else{
            println("Can't Load!!")
        }
    }

    fun delete(){
        if (LoadMatch.gameID.value >= 1
        ) {
            try {
                val conn: Connection? = MainController.getConnection()
                val s: PreparedStatement
                s = conn!!.prepareStatement("delete from " + MainController.tableName + " where gameID = ?")
                s.setInt(1, LoadMatch.gameID.value)
                deleteFromTable(model)
                val count = s.executeUpdate()
                if (count == 0) {
                    JOptionPane.showMessageDialog(null, "Record Not Found \nPlease enter a valid SSN Number")
                }
                LoadMatch.gameID.set(0)
                LoadMatch.teamName.set("")
                LoadMatch.teamGoals.set(0)
                LoadMatch.teamPoints.set(0)
                LoadMatch.oppName.set("")
                LoadMatch.oppGoals.set(0)
                LoadMatch.oppPoints.set(0)
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

    private fun deleteFromTable(model: MatchModel){
        var id = 0
        MainController.listOfMatches.forEachIndexed { index, match ->
            if(match.gameID == model.gameID.value && index != -1){
                id = index
            }
        }
        MainController.listOfMatches.removeAt(id)
    }

    //CREATE/INSERT/UPDATE/DELETE/DROP/etc
    @Throws(SQLException::class)
    fun executeUpdate(conn: Connection, command: String?): Boolean {
        var stmt: Statement? = null
        return try {
            stmt = conn.createStatement()
            stmt.executeUpdate(command) // This will throw a SQLException if it fails
            true
        } finally {

            // This will run whether we throw an exception or not
            if (stmt != null) {
                stmt.close()
            }
        }
    }

    fun add(){
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
                val match = Match(
                    model.gameID.value,
                    model.teamName.value,
                    model.teamGoals.value,
                    model.teamPoints.value,
                    model.oppName.value,
                    model.oppGoals.value,
                    model.oppPoints.value
                )
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

    fun createMatch(Match:Match){
        MainController.listOfMatches.add(Match)
    }

    fun update(){
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
                MainController.listOfMatches.asyncItems { MainController.matchList() }
            } catch (ex: SQLException) {
                ex.printStackTrace()
            }
        }
        else{
                println("Can't update!!")
            }
    }

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