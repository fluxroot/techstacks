rootProject.name = "techstacks"

val projects = listOf(
    "quarkus"
).forEach { configureProject(it) }

fun configureProject(projectName: String) {
    include(projectName)
    val project = project(":${projectName}")
    project.buildFileName = "${projectName}.gradle.kts"
    project.projectDir = rootDir.resolve(projectName)
}
