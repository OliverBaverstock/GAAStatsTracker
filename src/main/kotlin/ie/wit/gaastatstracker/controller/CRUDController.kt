package ie.wit.gaastatstracker.controller

import ie.wit.gaastatstracker.controller.MainController
import ie.wit.gaastatstracker.view.LoadMatch
import tornadofx.Controller
import java.sql.Connection
import java.sql.DriverManager.getConnection
import java.sql.PreparedStatement
import java.sql.SQLException
import javax.swing.JOptionPane


class CRUDController: Controller() {

    val MainController: MainController by inject()
    val LoadMatch: LoadMatch by inject()

    fun search(){
        try{
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

}