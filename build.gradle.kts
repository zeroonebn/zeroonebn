/*plugins {
    `maven-publish`
    signing
}*/

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "org.gradle.signing")

    group = "xyz.zeroonebn"
    version = "0.0.2-SNAPSHOT"

    extra["springBootVersion"] = "2.4.4"
    extra["jt400Version"] = "10.5"
    extra["vaadinVersion"] = "14.5.0"
    extra["karibudslVersion"] = "1.0.4"

    val isReleaseVersion = !version.toString().endsWith("SNAPSHOT")
    extra["isReleaseVersion"] = isReleaseVersion

    configure<JavaPluginExtension> {
        withJavadocJar()
        withSourcesJar()
        sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
        targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
    }

    configure<SigningExtension> {
        afterEvaluate {
            isRequired = isReleaseVersion
            sign((this.extensions["publishing"] as PublishingExtension).publications["mavenJava"])
        }

    }
/*    signing {
        sign(publishing.publications["mavenJava"])
    }*/
    configure<PublishingExtension> {
        publications {
            repositories {
                maven {
                    url = uri(if (isReleaseVersion) "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                    else "https://s01.oss.sonatype.org/content/repositories/snapshots/")
                    credentials {
                        username = if (hasProperty("ossrhUsername")) properties["ossrhUsername"].toString() else "Unknown user"
                        password = if (hasProperty("ossrhPassword")) properties["ossrhPassword"].toString() else "Unknown password"
                    }
                }
            }
            create<MavenPublication>("mavenJava") {
                from(components["java"])
                pom {
                    afterEvaluate {
                        val url: String by extra;
                        this@pom.name.set(name)
                        this@pom.description.set(description)
                        this@pom.url.set(url)
                    }
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("dferrand")
                            name.set("Damien Ferrand")
                            email.set("dferrand@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/zeroonebn/zeroonebn")
                        developerConnection.set("scm:git:ssh://git@github.com:zeroonebn/zeroonebn.git")
                        url.set("https://github.com/zeroonebn/zeroonebn")
                    }
                }
            }
        }
    }
}
