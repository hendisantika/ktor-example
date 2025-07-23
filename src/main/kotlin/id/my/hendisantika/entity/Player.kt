package id.my.hendisantika.id.my.hendisantika.entity

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

/**
 * Created by IntelliJ IDEA.
 * Project : ktor-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 23/07/25
 * Time: 07.32
 * To change this template use File | Settings | File Templates.
 */
@Serializable
data class Player(
    val id: Int,
    val name: String,
    val profilePic: String?
)

object Players : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val profilePic = varchar("profilePic", 1024).nullable()

    override val primaryKey = PrimaryKey(id)
}