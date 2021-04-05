plugins {
    java
    checkstyle
    pmd
    id("com.github.spotbugs") version "4.7.0"
    id("org.springframework.boot") version "2.4.4"
    id("com.google.cloud.tools.jib") version "2.8.0"
}

dependencies {
    compileOnly(Libs.lombok)
    annotationProcessor(Libs.lombok)
    testCompileOnly(Libs.lombok)
    testAnnotationProcessor(Libs.lombok)

    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation(Libs.springBootStarterWeb)
    testImplementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    testImplementation(Libs.springBootStarterTest) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    spotbugsPlugins(Libs.findSecBugs)

    testImplementation(Libs.archUnit)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
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
        image = "fluxroot/techstacks-spring"
        tags = setOf(project.version.toString())
    }
}

object Libs {
    const val lombok = "org.projectlombok:lombok:1.18.18"

    const val springBootStarterWeb = "org.springframework.boot:spring-boot-starter-web"
    const val springBootStarterTest = "org.springframework.boot:spring-boot-starter-test"

    const val findSecBugs = "com.h3xstream.findsecbugs:findsecbugs-plugin:1.11.0"

    const val archUnit = "com.tngtech.archunit:archunit-junit5:0.17.0"
}
