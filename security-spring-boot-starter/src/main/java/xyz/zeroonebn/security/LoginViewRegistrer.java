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

import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

/**
 * Registers LoginView route.
 *
 * @author Damien Ferrand
 */

public class LoginViewRegistrer implements VaadinServiceInitListener {

    private final SecurityProperties securityProperties;

    public LoginViewRegistrer(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void serviceInit(ServiceInitEvent event) {
        RouteConfiguration configuration = RouteConfiguration.forApplicationScope();

        configuration.setRoute(securityProperties.getLoginRoute(),
                LoginView.class);
    }
}
