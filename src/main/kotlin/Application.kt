package id.my.hendisantika

import id.my.hendisantika.repository.UserDaoImpl
import id.my.hendisantika.repository.UserRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

// Create a singleton instance of UserDaoImpl to be used throughout the application
val dao: UserRepository = UserDaoImpl()

fun Application.module() {
    DatabaseFactory.init()

    configureSerialization()
    configureRouting()
}
