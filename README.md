# Todo List Application

A multi-project Gradle setup with a Ktor backend and shared Kotlin Multiplatform code for TypeScript integration.

## Project Structure

- **api**: Ktor backend project
- **shared**: Kotlin Multiplatform project with data classes shared between backend and frontend
- **front**: The VueJs project front-end.

## Setup
### Building the Project

```bash
./gradlew build
```

### Running the API Server

```bash
./gradlew :api:run
```

The server will start on http:127.0.0.1:8080

## Shared Module

The shared module contains data classes that can be used in both the Ktor backend and a TypeScript frontend. It's configured as a Kotlin Multiplatform project with JVM and JS targets.
