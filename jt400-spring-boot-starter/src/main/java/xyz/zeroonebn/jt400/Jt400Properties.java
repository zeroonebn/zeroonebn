/*
 *    Copyright 2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package xyz.zeroonebn.jt400;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JT400 configuration properties.
 *
 * @author Damien Ferrand
 */
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
