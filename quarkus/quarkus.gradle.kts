plugins {
    java
    checkstyle
    pmd
    id("com.github.spotbugs") version "4.7.0"
    id("io.quarkus") version "1.12.2.Final"
}

dependencies {
    compileOnly(Libs.lombok)
    annotationProcessor(Libs.lombok)
    testCompileOnly(Libs.lombok)
    testAnnotationProcessor(Libs.lombok)

    implementation(enforcedPlatform(Libs.quarkusPlatform))
    implementation(Libs.quarkusContainerImageJib)
    implementation(Libs.quarkusRestEasyJackson)

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

object Libs {
    const val lombok = "org.projectlombok:lombok:1.18.18"

    const val quarkusPlatform = "io.quarkus:quarkus-universe-bom:1.12.2.Final"
    const val quarkusContainerImageJib = "io.quarkus:quarkus-container-image-jib"
    const val quarkusRestEasyJackson = "io.quarkus:quarkus-resteasy-jackson"

    const val findSecBugs = "com.h3xstream.findsecbugs:findsecbugs-plugin:1.11.0"

    const val archUnit = "com.tngtech.archunit:archunit-junit5:0.17.0"
}
