plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinPluginSerialization)
    alias(libs.plugins.detekt)
    alias(libs.plugins.jib)
}

dependencies {
    implementation(libs.kotlinxSerialization)

    implementation(platform(libs.ktorPlatform))
    implementation(libs.ktorServerCore)
    implementation(libs.ktorServerNetty)
    implementation(libs.ktorSerialization)
    implementation(libs.logbackClassic)

    implementation(libs.koinKtor)

    testImplementation(platform(libs.junitPlatform))
    testImplementation(libs.junitJupiterApi)
    testRuntimeOnly(libs.junitJupiterEngine)

    testImplementation(libs.assertjCore)

    testImplementation(libs.archUnit)
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
