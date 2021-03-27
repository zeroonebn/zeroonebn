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
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.zeroonebn.jt400.Jt400AutoConfiguration;
import xyz.zeroonebn.security.SecurityConfiguration;

/**
 * IBM i security auto configuration.
 *
 * @author Damien Ferrand
 */
@Configuration
@ConditionalOnClass(AS400.class)
@AutoConfigureBefore(SecurityConfiguration.class)
@AutoConfigureAfter(Jt400AutoConfiguration.class)
public class As400SecurityAutoConfiguration {


    @Bean("userDetailsService")
    @ConditionalOnBean(AS400.class)
    @ConditionalOnMissingBean(UserDetailsService.class)
    public As400UserDetailsService as400UserDetailsService(AS400 as400) {
        return new As400UserDetailsService(as400);
    }

    @Bean
    @ConditionalOnBean({AS400.class, UserDetailsService.class})
    @ConditionalOnMissingBean(AuthenticationProvider.class)
    public As400AuthenticationProvider as400AuthenticationProvider(AS400 as400,
                                                                   UserDetailsService userDetailsService) {
        return new As400AuthenticationProvider(as400, userDetailsService);
    }

    @Bean
    @ConditionalOnBean({As400UserDetailsService.class, As400AuthenticationProvider.class})
    public As400SecurityConfiguration as400SecurityConfiguration(AS400 as400) {
        return new As400SecurityConfiguration(as400AuthenticationProvider(as400,
                as400UserDetailsService(as400)));
    }
}
