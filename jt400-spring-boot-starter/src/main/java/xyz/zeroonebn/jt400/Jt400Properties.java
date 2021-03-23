package xyz.zeroonebn.jt400;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jt400")
public class Jt400Properties {

    /**
     * IBM i host.
     */
    private String host = "localhost";

    /**
     * IBM i user.
     */
    private String user = "*CURRENT";

    /**
     * IBM i password.
     */
    private String password = "*CURRENT";

    /**
     * JDBC Translate binary.
     */
    private boolean translateBinary = true;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTranslateBinary() {
        return translateBinary;
    }

    public void setTranslateBinary(boolean translateBinary) {
        this.translateBinary = translateBinary;
    }
}
