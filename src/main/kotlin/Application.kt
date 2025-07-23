package id.my.hendisantika

import id.my.hendisantika.id.my.hendisantika.repository.UserDaoImpl
import id.my.hendisantika.id.my.hendisantika.repository.UserRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    UserDaoImpl()
    DatabaseFactory.init()

    configureSerialization()
    configureRouting()
}
