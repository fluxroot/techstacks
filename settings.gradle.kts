rootProject.name = "techstacks"

include(
    "quarkus-imperative",
    "quarkus-reactive",
    "spring-servlet",
    "spring-reactive",
    "ktor",
    "nest"
)

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        jcenter {
            content {
                // TODO: Remove when kotlinx-html-jvm is available on Maven Central
                includeModule("org.jetbrains.kotlinx", "kotlinx-html-jvm")
            }
        }
    }
}
