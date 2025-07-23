# ktor-sample

This project was created using the [Ktor Project Generator](https://start.ktor.io).

Here are some useful links to get you started:

- [Ktor Documentation](https://ktor.io/docs/home.html)
- [Ktor GitHub page](https://github.com/ktorio/ktor)
- The [Ktor Slack chat](https://app.slack.com/client/T09229ZC6/C0A974TJ9). You'll need
  to [request an invite](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up) to join.

## Features

Here's a list of features included in this project:

| Name                                                                   | Description                                                                        |
|------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| [Routing](https://start.ktor.io/p/routing)                             | Provides a structured routing DSL                                                  |
| [Content Negotiation](https://start.ktor.io/p/content-negotiation)     | Provides automatic content conversion according to Content-Type and Accept headers |
| [kotlinx.serialization](https://start.ktor.io/p/kotlinx-serialization) | Handles JSON serialization using kotlinx.serialization library                     |

## Building & Running

To build or run the project, use one of the following tasks:

| Task                          | Description                                                          |
|-------------------------------|----------------------------------------------------------------------|
| `./gradlew test`              | Run the tests                                                        |
| `./gradlew build`             | Build everything                                                     |
| `buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `buildImage`                  | Build the docker image to use with the fat JAR                       |
| `publishImageToLocalRegistry` | Publish the docker image locally                                     |
| `run`                         | Run the server                                                       |
| `runDocker`                   | Run using the local docker image                                     |

## Testing with TestContainers

This project uses [TestContainers](https://www.testcontainers.org/) for integration testing with PostgreSQL.
TestContainers automatically starts a PostgreSQL container during test execution, allowing for isolated and reproducible
tests without the need for a local PostgreSQL installation.

To run the tests with TestContainers:

```bash
./gradlew test
```

The tests will:

1. Start a PostgreSQL container
2. Initialize the database schema
3. Run tests against the database
4. Automatically shut down the container when tests complete

### Test Implementation

The database tests are implemented in `src/test/kotlin/id/my/hendisantika/DatabaseTest.kt` and include:

- Testing database connection
- Adding and retrieving users
- Updating users
- Deleting users
- Retrieving all users

### Requirements

To run the tests with TestContainers, you need:

- Docker installed and running on your machine
- JDK 11 or higher
- Gradle

If the server starts successfully, you'll see the following output:

```
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```

## API Endpoints

This project provides the following API endpoints for managing user data:

### Get Hello World

```bash
curl -X GET http://localhost:8080/
```

### Add a new user

```bash
curl -X POST http://localhost:8080/adduser \
  -d "name=John Doe" \
  -d "profilePic=https://example.com/profile.jpg"
```

Note: The `profilePic` parameter is optional.

### Get all users

```bash
curl -X GET http://localhost:8080/users
```

### Get a specific user by ID

```bash
curl -X GET http://localhost:8080/user/1
```

### Update a user

```bash
curl -X PUT http://localhost:8080/user/1 \
  -d "name=Updated Name" \
  -d "profilePic=https://example.com/new-profile.jpg"
```

Note: The `profilePic` parameter is optional.

### Delete a user

```bash
curl -X DELETE http://localhost:8080/user/1
```

