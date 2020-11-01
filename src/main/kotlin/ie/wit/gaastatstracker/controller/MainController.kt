package ie.wit.gaastatstracker.controller

import ie.wit.gaastatstracker.models.Match
import ie.wit.gaastatstracker.view.LoadMatch
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller
import tornadofx.SortedFilteredList
import tornadofx.asObservable
import tornadofx.singleAssign

import java.sql.*
import java.util.*
import javax.swing.JOptionPane
//import java.util.*
import kotlin.collections.ArrayList


class MainController: Controller() {
   // class MainController : Controller()

    /** The name of the MySQL account to use (or empty for anonymous)  */
    private val userName = "root"

    /** The password for the MySQL account (or empty for anonymous)  */
    private val password = "roottoor"

    /** The name of the computer running MySQL  */
    private val serverName = "localhost"

    /** The port of the MySQL server (default is 8889)  */
    private val portNumber = 3306

    /** The name of the database we are testing with (this default is installed with MySQL)  */
    private val dbName = "matchesDB"

    /** The name of the table we are testing with  */
    val tableName = "matches"

    private var conn: Connection? = null

    fun matchList(): List<Match> {
        val matchList = mutableListOf<Match>()
            conn = getConnection()
            val st = conn!!.createStatement()
            val rs = st.executeQuery("SELECT * FROM $tableName")
            while (rs.next()) {
                    val gameID = rs.getInt("gameID")
                    val teamName = rs.getString("teamName")
                    val teamGoals = rs.getInt("teamGoals")
                    val teamPoints = rs.getInt("teamPoints")
                    val oppName = rs.getString("oppName")
                    val oppGoals = rs.getInt("oppGoals")
                    val oppPoints = rs.getInt("oppPoints")
                    matchList += Match(gameID, teamName, teamGoals, teamPoints, oppName, oppGoals, oppPoints)
            }

        return matchList
    }

    val listOfMatches: ObservableList<Match> = matchList().asObservable()

    //val items: ObservableList<Match> by singleAssign()

    @Throws(SQLException::class)
    fun getConnection(): Connection? {
        var conn: Connection? = null
        val connectionProps = Properties()
        connectionProps["user"] = userName
        connectionProps["password"] = password
        conn = DriverManager.getConnection(
            "jdbc:mysql://"
                    + serverName + ":" + portNumber + "/" + dbName,
            connectionProps
        )
        return conn
    }

    fun connectSQL() {

        // Connect to MySQL
        try {
            conn = getConnection()
            matchList()
            println("Connected to database")
        } catch (e: SQLException) {
            println("2ERROR: Could not connect to the database")
            e.printStackTrace()
            matchList()
            return
        }
    }
}