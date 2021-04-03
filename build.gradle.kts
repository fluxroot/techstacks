allprojects {
    repositories {
        mavenCentral()
    }

    group = "dev.nonava.techstacks"
    version = "1.0.0"

    tasks.withType<AbstractArchiveTask> {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
    }
}
