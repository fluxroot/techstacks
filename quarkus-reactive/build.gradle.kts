plugins {
    java
    checkstyle
    pmd
    alias(libs.plugins.spotbugs)
    alias(libs.plugins.quarkus)
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)

    implementation(enforcedPlatform(libs.quarkusPlatform))
    implementation(libs.quarkusContainerImageJib)
    implementation(libs.quarkusRestEasyReactiveJackson)

    spotbugsPlugins(libs.findSecBugs)

    testImplementation(libs.archUnit)
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
