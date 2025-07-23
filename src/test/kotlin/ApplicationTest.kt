package id.my.hendisantika

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import org.junit.After
import org.junit.Before
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Test
import kotlin.test.assertEquals

@Testcontainers
class ApplicationTest {

    companion object {
        @Container
        val postgresContainer = PostgreSQLContainer<Nothing>("postgres:17.5-alpine3.22").apply {
            withDatabaseName("testdb")
            withUsername("testuser")
            withPassword("testpassword")
        }
    }

    @Before
    fun setUp() {
        // Start the container if it's not running
        if (!postgresContainer.isRunning) {
            postgresContainer.start()
        }

        // Initialize the database with TestContainers connection parameters
        DatabaseFactory.init(
            url = postgresContainer.jdbcUrl,
            driver = postgresContainer.driverClassName,
            user = postgresContainer.username,
            password = postgresContainer.password
        )
    }

    @After
    fun tearDown() {
        // No need to stop the container as TestContainers will handle it
    }

    // Custom module function that doesn't initialize the database again
    fun Application.testModule() {
        // Skip DatabaseFactory.init() since it's already initialized in setUp()
        configureSerialization()
        configureRouting()
    }

    @Test
    fun testRoot() = testApplication {
        application {
            // Use our custom module that doesn't initialize the database again
            testModule()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }
}
