package id.my.hendisantika

import id.my.hendisantika.entity.Players
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Created by IntelliJ IDEA.
 * Project : ktor-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 23/07/25
 * Time: 07.29
 * To change this template use File | Settings | File Templates.
 */
object DatabaseFactory {
    // Default connection parameters
    private const val DEFAULT_URL = "jdbc:postgresql://localhost:5433/player"
    private const val DEFAULT_DRIVER = "org.postgresql.Driver"
    private const val DEFAULT_USER = "yu71"
    private const val DEFAULT_PASSWORD = "53cret"

    private var database: Database? = null

    fun init(
        url: String = DEFAULT_URL,
        driver: String = DEFAULT_DRIVER,
        user: String = DEFAULT_USER,
        password: String = DEFAULT_PASSWORD
    ) {
        database = Database.connect(
            url = url,
            driver = driver,
            user = user,
            password = password
        )
        transaction(database!!) {
            SchemaUtils.create(Players)
        }
    }

    fun getDatabase(): Database {
        return database ?: throw IllegalStateException("Database has not been initialized")
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO, db = database) { block() }
}