package id.my.hendisantika.id.my.hendisantika.repository

import id.my.hendisantika.id.my.hendisantika.entity.Player

/**
 * Created by IntelliJ IDEA.
 * Project : ktor-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 23/07/25
 * Time: 07.34
 * To change this template use File | Settings | File Templates.
 */
interface UserRepository {
    suspend fun allUsers(): List<Player>
}