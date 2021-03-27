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

package xyz.zeroonebn.security.as400;

import com.ibm.as400.access.AS400;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import xyz.zeroonebn.security.SecurityConfiguration;

/**
 * IBM i Web Security configurer.
 *
 * @author Damien Ferrand
 */
@AutoConfigureBefore(SecurityConfiguration.class)
@ConditionalOnClass(AS400.class)
@Configuration
@ConditionalOnBean(As400AuthenticationProvider.class)
@Order(110)
public class As400SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final As400AuthenticationProvider as400AuthenticationProvider;

    public As400SecurityConfiguration(As400AuthenticationProvider as400AuthenticationProvider) {
        this.as400AuthenticationProvider = as400AuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(as400AuthenticationProvider);
    }
}
