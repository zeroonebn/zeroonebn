subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    group = "xyz.zeroonebn"
    version = "0.0.1-SNAPSHOT"

    extra["springBootVersion"] = "2.4.4"
    extra["jt400Version"] = "10.5"
    extra["vaadinVersion"] = "14.5.0"
    extra["karibudslVersion"] = "1.0.4"

    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
                pom {
                    afterEvaluate {
                        val url: String by extra;
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
