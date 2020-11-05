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

    /** The name of the MySQL account to use (or empty for anonymous)  */
    private val userName = "root"

    /** The password for the MySQL account (or empty for anonymous)  */
    private val password = "roottoor"

    /** The name of the computer running MySQL  */
    private val serverName = "localhost"

    /** The port of the MySQL server  */
    private val portNumber = 3306

    /** The name of the database */
    private val dbName = "matchesDB"

    /** The name of the mysql table */
    val tableName = "matches"

    private var conn: Connection? = null

    //Pulls the data from the mysql database and inputs it into a list of type match
    fun matchList(): List<Match> {
        //Creating a mutable list matchList of type match
        val matchList = mutableListOf<Match>()
            conn = getConnection()
            val st = conn!!.createStatement()
        //selects all users from the matches table
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

    //Creates an observable list of type match  which is based on the returned list from matchList() as Observable
    val listOfMatches: ObservableList<Match> = matchList().asObservable()

    //Gets connection to the database
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

    //Connects to the DB and also calls the matchList function to initialize the listOfMatches
    fun connectSQL() {
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