plugins {
    java
    checkstyle
    pmd
    alias(libs.plugins.spotbugs)
    alias(libs.plugins.springBoot)
    alias(libs.plugins.jib)
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)

    implementation(libs.result)
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation(libs.springBootStarterWeb)
    testImplementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    testImplementation(libs.springBootStarterTest) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    spotbugsPlugins(libs.findSecBugs)

    testImplementation(libs.archUnit)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    config = resources.text.fromFile("$projectDir/config/checkstyle/checkstyle.xml")
    configDirectory.set(file("$projectDir/config/checkstyle"))
}

pmd {
    ruleSets = listOf()
    ruleSetConfig = resources.text.fromFile("$projectDir/config/pmd/main-ruleset.xml")
}

tasks.pmdTest {
    ruleSets = listOf()
    ruleSetConfig = resources.text.fromFile("$projectDir/config/pmd/test-ruleset.xml")
}

jib {
    from {
        image = "gcr.io/distroless/java:11"
    }
    to {
        image = "fluxroot/techstacks-spring-servlet"
        tags = setOf(project.version.toString())
    }
}
