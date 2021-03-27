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

description = "JT400 autoconfiguration"
extra["url"] = "https://github.com/zeroonebn/zeroonebn/tree/main/jt400-spring-boot-starter"

val springBootVersion: String by extra
val jt400Version: String by extra

dependencies {
    api("org.springframework.boot:spring-boot-starter:$springBootVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    api("net.sf.jt400:jt400-jdk8:$jt400Version")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
