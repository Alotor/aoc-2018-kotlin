plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.10")
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "aoc2018.AppKt"
}

tasks {
    withType<Test> {
        testLogging.showStandardStreams = true
    }
}
