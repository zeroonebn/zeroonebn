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
import com.ibm.as400.access.AS400SecurityException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

/**
 * Authentication provider authenticating against IBM i user profiles.
 *
 * @author Damien Ferrand
 */
public class As400AuthenticationProvider implements AuthenticationProvider {
    private final AS400 as400;

    private final UserDetailsService userDetailsService;

    public As400AuthenticationProvider(AS400 as400,
                                       UserDetailsService userDetailsService) {
        this.as400 = as400;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            as400.authenticate(name, password);
            return new UsernamePasswordAuthenticationToken(name, password,
                    userDetailsService.loadUserByUsername(name).getAuthorities());
        } catch (AS400SecurityException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
