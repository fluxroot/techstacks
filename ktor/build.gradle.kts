plugins {
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.serialization") version "1.4.31"
    id("io.gitlab.arturbosch.detekt") version "1.16.0"
    id("com.google.cloud.tools.jib") version "2.8.0"
}

repositories {
    jcenter {
        content {
            // TODO: Remove when kotlinx-html-jvm is available on Maven Central
            includeModule("org.jetbrains.kotlinx", "kotlinx-html-jvm")
        }
    }
}

dependencies {
    implementation(Libs.kotlinxSerialization)

    implementation(platform(Libs.ktorPlatform))
    implementation(Libs.ktorServerCore)
    implementation(Libs.ktorServerNetty)
    implementation(Libs.ktorSerialization)
    implementation(Libs.logbackClassic)

    implementation(Libs.koinKtor)

    testImplementation(platform(Libs.junitPlatform))
    testImplementation(Libs.junitJupiterApi)
    testRuntimeOnly(Libs.junitJupiterEngine)

    testImplementation(Libs.assertjCore)

    testImplementation(Libs.archUnit)
}

tasks.test {
    useJUnitPlatform()
}

jib {
    from {
        image = "gcr.io/distroless/java:11"
    }
    to {
        image = "fluxroot/techstacks-ktor"
        tags = setOf(project.version.toString())
    }
}

object Libs {
    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0"

    const val ktorPlatform = "io.ktor:ktor-bom:1.5.2"
    const val ktorServerCore = "io.ktor:ktor-server-core"
    const val ktorServerNetty = "io.ktor:ktor-server-netty"
    const val ktorSerialization = "io.ktor:ktor-serialization"
    const val logbackClassic = "ch.qos.logback:logback-classic:1.2.3"

    const val koinKtor = "io.insert-koin:koin-ktor:3.0.1-beta-2"

    const val junitPlatform = "org.junit:junit-bom:5.7.1"
    const val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api"
    const val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine"

    const val assertjCore = "org.assertj:assertj-core:3.19.0"

    const val archUnit = "com.tngtech.archunit:archunit-junit5:0.17.0"
}
