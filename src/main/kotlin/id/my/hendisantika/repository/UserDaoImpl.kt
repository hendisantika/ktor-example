package id.my.hendisantika.repository

import id.my.hendisantika.DatabaseFactory.dbQuery
import id.my.hendisantika.entity.Player
import id.my.hendisantika.entity.Players
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

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
        Players.selectAll().map(::resultRowToPlayer)
    }

    override suspend fun user(id: Int): Player? = dbQuery {
        Players.select {
            Players.id eq id
        }
            .map(::resultRowToPlayer)
            .singleOrNull()
    }

    override suspend fun addNewUser(name: String, profilePic: String?): Player? = dbQuery {
        val insertStatement = Players.insert {
            it[Players.name] = name
            it[Players.profilePic] = profilePic
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToPlayer)
    }

    override suspend fun editUser(id: Int, name: String, profilePic: String?): Boolean = dbQuery {
        Players.update({ Players.id eq id }) {
            it[Players.name] = name
            it[Players.profilePic] = profilePic
        } > 0
    }

    override suspend fun deleteUser(id: Int): Boolean = dbQuery {
        Players.deleteWhere { Players.id eq id } > 0
    }

    private fun resultRowToPlayer(row: ResultRow) = Player(
        id = row[Players.id],
        name = row[Players.name],
        profilePic = row[Players.profilePic]
    )
}