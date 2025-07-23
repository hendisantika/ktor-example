package id.my.hendisantika

import id.my.hendisantika.repository.UserDaoImpl
import id.my.hendisantika.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@Testcontainers
class DatabaseTest {

    companion object {
        @Container
        val postgresContainer = PostgreSQLContainer<Nothing>("postgres:17.5-alpine3.22").apply {
            withDatabaseName("testdb")
            withUsername("testuser")
            withPassword("testpassword")
        }
    }

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        // Start the container if it's not running
        if (!postgresContainer.isRunning) {
            postgresContainer.start()
        }

        println("[DEBUG_LOG] PostgreSQL container started at: ${postgresContainer.jdbcUrl}")

        // Initialize the database with TestContainers connection parameters
        DatabaseFactory.init(
            url = postgresContainer.jdbcUrl,
            driver = postgresContainer.driverClassName,
            user = postgresContainer.username,
            password = postgresContainer.password
        )

        // Initialize the repository
        userRepository = UserDaoImpl()
    }

    @After
    fun tearDown() {
        // No need to stop the container as TestContainers will handle it
    }

    @Test
    fun testDatabaseConnection() {
        // This test verifies that we can connect to the database
        val database = DatabaseFactory.getDatabase()
        assertNotNull(database, "Database connection should be established")
        println("[DEBUG_LOG] Database connection successful")
    }

    @Test
    fun testAddAndRetrieveUser() = runBlocking {
        // Add a new user
        val name = "Test User"
        val profilePic = "https://example.com/test.jpg"

        val addedUser = userRepository.addNewUser(name, profilePic)
        assertNotNull(addedUser, "User should be added successfully")
        assertEquals(name, addedUser.name, "User name should match")
        assertEquals(profilePic, addedUser.profilePic, "User profile pic should match")

        // Retrieve the user
        val retrievedUser = userRepository.user(addedUser.id)
        assertNotNull(retrievedUser, "User should be retrieved successfully")
        assertEquals(addedUser.id, retrievedUser.id, "User ID should match")
        assertEquals(name, retrievedUser.name, "User name should match")
        assertEquals(profilePic, retrievedUser.profilePic, "User profile pic should match")

        println("[DEBUG_LOG] User added and retrieved successfully: $retrievedUser")
    }

    @Test
    fun testUpdateUser() = runBlocking {
        // Add a new user
        val originalName = "Original Name"
        val originalProfilePic = "https://example.com/original.jpg"

        val addedUser = userRepository.addNewUser(originalName, originalProfilePic)
        assertNotNull(addedUser, "User should be added successfully")

        // Update the user
        val updatedName = "Updated Name"
        val updatedProfilePic = "https://example.com/updated.jpg"

        val updateResult = userRepository.editUser(addedUser.id, updatedName, updatedProfilePic)
        assertTrue(updateResult, "User update should be successful")

        // Retrieve the updated user
        val updatedUser = userRepository.user(addedUser.id)
        assertNotNull(updatedUser, "User should be retrieved successfully")
        assertEquals(addedUser.id, updatedUser.id, "User ID should match")
        assertEquals(updatedName, updatedUser.name, "Updated user name should match")
        assertEquals(updatedProfilePic, updatedUser.profilePic, "Updated user profile pic should match")

        println("[DEBUG_LOG] User updated successfully: $updatedUser")
    }

    @Test
    fun testDeleteUser() = runBlocking {
        // Add a new user
        val name = "User to Delete"
        val profilePic = "https://example.com/delete.jpg"

        val addedUser = userRepository.addNewUser(name, profilePic)
        assertNotNull(addedUser, "User should be added successfully")

        // Delete the user
        val deleteResult = userRepository.deleteUser(addedUser.id)
        assertTrue(deleteResult, "User deletion should be successful")

        // Try to retrieve the deleted user
        val deletedUser = userRepository.user(addedUser.id)
        assertEquals(null, deletedUser, "Deleted user should not be found")

        println("[DEBUG_LOG] User deleted successfully")
    }

    @Test
    fun testGetAllUsers() = runBlocking {
        // Add multiple users
        val user1 = userRepository.addNewUser("User 1", "https://example.com/user1.jpg")
        val user2 = userRepository.addNewUser("User 2", "https://example.com/user2.jpg")
        val user3 = userRepository.addNewUser("User 3", "https://example.com/user3.jpg")

        // Get all users
        val allUsers = userRepository.allUsers()

        // Verify that all added users are in the list
        assertTrue(allUsers.any { it.id == user1!!.id }, "User 1 should be in the list")
        assertTrue(allUsers.any { it.id == user2!!.id }, "User 2 should be in the list")
        assertTrue(allUsers.any { it.id == user3!!.id }, "User 3 should be in the list")

        println("[DEBUG_LOG] All users retrieved successfully: ${allUsers.size} users")
    }
}