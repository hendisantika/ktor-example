package id.my.hendisantika

import io.ktor.server.application.*
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
            val profilePic = formParameters.get("profilePic") // Make profilePic optional
            val user = dao.addNewUser(name, profilePic)
            if (user != null) {
                call.respond(user)
            } else {
                call.respond(mapOf("error" to "Failed to add user"))
            }
        }

        get("/users") {
            val listOfUsers = dao.allUsers()
            if (listOfUsers.isEmpty()) {
                call.respond(mapOf("message" to "No users found"))
            } else {
                call.respond(listOfUsers)
            }
        }

        get("/user/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(mapOf("error" to "Invalid ID format"))
                return@get
            }

            val user = dao.user(id)
            if (user != null) {
                call.respond(user)
            } else {
                call.respond(mapOf("error" to "User not found"))
            }
        }

        put("/user/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(mapOf("error" to "Invalid ID format"))
                return@put
            }

            val formParameters = call.receiveParameters()
            val name = formParameters.getOrFail("name")
            val profilePic = formParameters.get("profilePic")

            val updated = dao.editUser(id, name, profilePic)
            if (updated) {
                call.respond(mapOf("message" to "User updated successfully"))
            } else {
                call.respond(mapOf("error" to "Failed to update user"))
            }
        }

        delete("/user/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(mapOf("error" to "Invalid ID format"))
                return@delete
            }

            val deleted = dao.deleteUser(id)
            if (deleted) {
                call.respond(mapOf("message" to "User deleted successfully"))
            } else {
                call.respond(mapOf("error" to "Failed to delete user"))
            }
        }
    }
}
