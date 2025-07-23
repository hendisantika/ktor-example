package id.my.hendisantika

import org.jetbrains.exposed.sql.Database

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
        Database.connect(
            url = "jdbc:postgresql://localhost:5433/ktor",
            driver = "org.postgresql.Driver",
            user = "yu71",
            password = "53cret"
        )
    }
}