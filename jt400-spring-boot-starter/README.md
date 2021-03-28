# jt400-spring-boot-starter

Spring Boot Starter for [JTopen](http://jt400.sourceforge.net/) (the opensource version of the IBM Toolbox for Java).

# Notable library imported

* [The JTOpen library](https://mvnrepository.com/artifact/net.sf.jt400/jt400)

# Notable Beans declared

* [AS400](https://javadoc.io/doc/net.sf.jt400/jt400-jdk8/latest/com/ibm/as400/access/AS400.html): Represents the authentication information and a set of connections to the IBM i host servers.
* [DataSource](https://docs.oracle.com/javase/8/docs/api/javax/sql/DataSource.html): A factory for JDBC connexions. The bean is actually an [ASJDBCDataSource](https://javadoc.io/doc/net.sf.jt400/jt400-jdk8/latest/com/ibm/as400/access/AS400JDBCDataSource.html) using the AS400 Bean

# Notable properties

* _jt400.host_: IP or host name of the IBM i the AS400 Bean (and consequently the DataSource Bean) connects to. Defaults to localhost.
* _jt400.user_: User profile to use to connect to the IBM i. Defaults to *CURRENT.
* _jt400.password_: Password to use to connect to the IBM i. Defaults to *CURRENT.

Those default values will only work when the application is running on an IBM i. They must be overridden when running on any other platform.