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

package xyz.zeroonebn.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Security properties.
 */

@ConfigurationProperties("security")
public class SecurityProperties {

    /**
     * Login route, defaults to login.
     */
    private String loginRoute = "login";

    /**
     * Login screen title, defaults to spring.application.name.
     */
    @Value("${spring.application.name:}")
    private String title;

    /**
     * Login screen description, defaults to empty.
     */
    private String description = "";

    public String getLoginRoute() {
        return loginRoute;
    }

    public void setLoginRoute(String loginRoute) {
        this.loginRoute = loginRoute;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
