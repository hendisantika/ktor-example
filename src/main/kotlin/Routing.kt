package id.my.hendisantika

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        post("/adduser") {
            val formParameters = call.receiveParameters()
            val name = formParameters.getOrFail("name")
            val profilePic = formParameters.getOrFail("profilePic")
            dao.addNewUser(name, profilePic)
            call.respond("User added successfully")
        }
    }
}
