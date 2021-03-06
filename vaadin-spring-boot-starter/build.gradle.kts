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

description = "Vaadin autoconfiguration"
extra["url"] = "https://github.com/zeroonebn/zeroonebn/tree/main/vaadin-spring-boot-starter"

val springBootVersion: String by extra
val vaadinVersion: String by extra
val karibudslVersion: String by extra

dependencies {
    api("com.github.mvysny.karibudsl:karibu-dsl:$karibudslVersion")
    api("com.vaadin:vaadin-spring-boot-starter:$vaadinVersion")
    api("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}