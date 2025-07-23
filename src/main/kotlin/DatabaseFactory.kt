package id.my.hendisantika

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
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
    fun init() {
        val database = Database.connect(
            url = "jdbc:postgresql://localhost:5432/z-draw",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "zaidkhan7860"
        )
        transaction(database) {
            SchemaUtils.create(Players)
        }
    }


}