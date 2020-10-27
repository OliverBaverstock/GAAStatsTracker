package ie.wit.gaastatstracker.util

import java.sql.Connection
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.TransactionManager

private var LOG_TO_CONSOLE: Boolean = false

fun newTransaction(): Transaction =
    TransactionManager.currentOrNew(Connection.TRANSACTION_SERIALIZABLE).apply {

}