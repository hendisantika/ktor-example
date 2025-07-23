package id.my.hendisantika.id.my.hendisantika.repository

import id.my.hendisantika.id.my.hendisantika.entity.Player
import id.my.hendisantika.id.my.hendisantika.entity.Players
import org.jetbrains.exposed.sql.selectAll

/**
 * Created by IntelliJ IDEA.
 * Project : ktor-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 23/07/25
 * Time: 07.36
 * To change this template use File | Settings | File Templates.
 */
class UserDaoImpl : UserRepository {
    override suspend fun allUsers(): List<Player> = dbQuery {
        Players.selectAll().map(::resultRowToArticle)
    }
}