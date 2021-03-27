# Zeroonebn

Zeroonebn (01 BN) is a tool designed to help create modern web applications for IBM i.

It integrates the following tools or libraries:
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Kotlin language](https://kotlinlang.org/)
* [Vaadin](https://vaadin.com/)
* [Karibu-DSL](https://github.com/mvysny/karibu-dsl)
* [JTOpen](http://jt400.sourceforge.net/)

# Getting started

To get started, include the dependencies you need in your build.gradle (or build.gradle.kts) file:

```kotlin
implementation("xyz.zeroonebn:vaadin-spring-boot-starter:0.0.1")
implementation("xyz.zeroonebn:security-spring-boot-starter:0.0.1")
implementation("xyz.zeroonebn:jt400-spring-boot-starter:0.0.1")
```

If you are using Maven, add those dependencies in your pom.xml file.

Those starters will pull the required dependencies and autoconfigure as much as possible.

If you run your application on IBM i, it will, by default, connect to the local IBM i with the user that runs the application (for database and other IBM i related accesses). During development, your application will likely run on a workstation that is not an IBM i, in this case you need to specify connection information to your IBM i in your application.properties file:
```properties
jt400.host=ibmi_host
jt400.user=ibmi_user
jt400.password=ibmi_password
```

It is generally a bad security practise to have a password in clear text in a file, you can instead put it in environment variable named JT400_PASSWORD.

# License

Zeroonebn is Open Source software released under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).