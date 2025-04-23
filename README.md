# Todo List Application

A multi-project Gradle setup with a Ktor backend and shared Kotlin Multiplatform code for TypeScript integration.

## Project Structure

- **api**: Ktor backend project
- **shared**: Kotlin Multiplatform project with data classes shared between backend and frontend

## Setup

### Prerequisites

- JDK 17 or higher
- Gradle 7.6 or higher

### Building the Project

```bash
./gradlew build
```

### Running the API Server

```bash
./gradlew :api:run
```

The server will start on http://localhost:8080

## Shared Module

The shared module contains data classes that can be used in both the Ktor backend and a TypeScript frontend. It's configured as a Kotlin Multiplatform project with JVM and JS targets.

### Sample Data Class

The project includes a sample `Todo` data class in the shared module:

```kotlin
@Serializable
data class Todo(
    val id: String,
    val title: String,
    val completed: Boolean = false,
    val priority: Priority = Priority.NORMAL
)

@Serializable
enum class Priority {
    LOW,
    NORMAL,
    HIGH
}
```

## Using in TypeScript

The shared module can be consumed in a TypeScript project. After building, the JavaScript artifacts will be available in the `shared/build/js` directory.

## Development

### Adding New Data Classes

To add new data classes to the shared module, create them in the `src/commonMain/kotlin/com/todolist/shared/models` directory.

### Extending the API

The API is built with Ktor. To add new endpoints, modify the `Application.kt` file in the `api` project.