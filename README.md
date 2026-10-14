# ktor-sample

[![Java CI with Gradle](https://github.com/hendisantika/ktor-example/actions/workflows/gradle.yml/badge.svg)](https://github.com/hendisantika/ktor-example/actions/workflows/gradle.yml)
![JDK](https://img.shields.io/badge/JDK-25-orange)
![Kotlin](https://img.shields.io/badge/Kotlin-2.4.21-7F52FF)
![Ktor](https://img.shields.io/badge/Ktor-3.6.0-087CFA)
![Gradle](https://img.shields.io/badge/Gradle-9.8.1-02303A)

This project was created using the [Ktor Project Generator](https://start.ktor.io).

Here are some useful links to get you started:

- [Ktor Documentation](https://ktor.io/docs/home.html)
- [Ktor GitHub page](https://github.com/ktorio/ktor)
- The [Ktor Slack chat](https://app.slack.com/client/T09229ZC6/C0A974TJ9). You'll need
  to [request an invite](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up) to join.

## Tech Stack

| Component      | Version |
|----------------|---------|
| JDK            | 25      |
| Kotlin         | 2.4.21  |
| Ktor           | 3.6.0   |
| Gradle         | 9.8.1   |
| Exposed        | 0.41.1  |
| PostgreSQL JDBC| 42.3.1  |
| Testcontainers | 1.21.4  |

The Kotlin toolchain is pinned to JDK 25 in `build.gradle.kts` (`kotlin { jvmToolchain(25) }`), so Gradle will
use (or provision) a JDK 25 regardless of the JDK used to launch it.

## Features

Here's a list of features included in this project:

| Name                                                                   | Description                                                                        |
|------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| [Routing](https://start.ktor.io/p/routing)                             | Provides a structured routing DSL                                                  |
| [Content Negotiation](https://start.ktor.io/p/content-negotiation)     | Provides automatic content conversion according to Content-Type and Accept headers |
| [kotlinx.serialization](https://start.ktor.io/p/kotlinx-serialization) | Handles JSON serialization using kotlinx.serialization library                     |
| [Exposed](https://github.com/JetBrains/Exposed)                        | Kotlin SQL framework used to access PostgreSQL                                     |

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
- JDK 25
- Gradle 9.8.1 (provided by the Gradle Wrapper, `./gradlew`)

## Continuous Integration

GitHub Actions ([`.github/workflows/gradle.yml`](.github/workflows/gradle.yml)) runs on every push and pull request to
`main`:

- **build** – sets up Temurin JDK 25 and runs `./gradlew build` (compiles and runs all tests, including the
  Testcontainers PostgreSQL tests)
- **dependency-submission** – submits the Gradle dependency graph to enable Dependabot alerts

## Running the Server

Start a PostgreSQL instance (for example with `docker compose -f compose.yml up -d`), then run:

```bash
./gradlew run
```

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

