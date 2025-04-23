plugins {
    kotlin("jvm") version "2.0.20" apply false
    kotlin("plugin.serialization") version "2.0.20" apply false
    kotlin("multiplatform") version "2.0.20" apply false
    id("io.ktor.plugin") version "3.1.2" apply false
}

allprojects {
    group = "com.todolist"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

// Task to copy .d.ts files to Vue project
tasks.register<Copy>("copyTypeScriptDefinitions") {
    description = "Compiles shared project and copies TypeScript definitions to Vue project"
    group = "build"

    // Depend on the shared project's JS compilation task
    dependsOn(":shared:jsProductionExecutableCompileSync")

    // Source file(s) to copy
    from("${project.projectDir}\\shared\\build\\compileSync\\js\\main\\productionExecutable\\kotlin\\todoList-shared.d.ts")

    // Destination directory
    into("${project.projectDir}\\front\\src\\shared")

    // Ensure the destination directory exists
    doFirst {
        file("${project.projectDir}\\front\\src\\shared").mkdirs()
    }

    // Transform the file content to rename the namespace, remove declare keyword, and remove unwanted methods
    filter { line ->
        var filteredLine = line.replace("com.estebandcprojects.models", "models")

        // Remove 'declare' keyword from namespace declaration
        filteredLine = filteredLine.replace("export declare namespace", "export namespace")

        // Skip lines containing hashCode, equals, copy methods and Companion object
        if (filteredLine.contains("hashCode()") || 
            filteredLine.contains("equals(other") || 
            filteredLine.contains("copy(")) {
            return@filter ""
        }

        filteredLine
    }
}
