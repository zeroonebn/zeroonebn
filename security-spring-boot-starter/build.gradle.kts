plugins {
    id("java-library")
    id("idea")
    id("maven-publish")
}

repositories {
    mavenCentral()
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

val springBootVersion: String by extra
val vaadinVersion: String by extra
val karibudslVersion: String by extra

dependencies {
    api("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    api(project(":vaadin-spring-boot-starter"))
    compileOnly(project(":jt400-spring-boot-starter"))
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
