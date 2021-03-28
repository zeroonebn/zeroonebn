# security-spring-boot-starter

Spring Boot starter for [Spring Security](https://spring.io/projects/spring-security) integration with [Vaadin](https://vaadin.com/). Based on [Paul Romer's tutorial](https://vaadin.com/learn/tutorials/securing-your-app-with-spring-security).

If an [AS400](https://javadoc.io/doc/net.sf.jt400/jt400-jdk8/latest/com/ibm/as400/access/AS400.html) Bean is declared, it will autoconfigure security to authenticate against IBM i user profiles.

# Notable libraries imported

* [Spring Boot starter for Spring Security](https://search.maven.org/artifact/org.springframework.boot/spring-boot-starter-security)
* [Zeroonebn Spring Boot starter for Vaadin](https://github.com/zeroonebn/zeroonebn/tree/main/vaadin-spring-boot-starter)

# Notable beans declared

The required beans to secure the Vaadin application are created by default.

A basic, not for production use, user (username is "user", password is "password") is created by default.

If an [AS400](https://javadoc.io/doc/net.sf.jt400/jt400-jdk8/latest/com/ibm/as400/access/AS400.html) bean is found, authentication against IBM i user profiles is configured.