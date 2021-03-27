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

import com.ibm.as400.access.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * UserDetailService working against IBM i user profiles.
 *
 * @author Damien Ferrand
 */
public class As400UserDetailsService implements UserDetailsService {
    private final AS400 as400;

    public As400UserDetailsService(AS400 as400) {
        this.as400 = as400;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            final User as400User = new User(as400, username);

            if (as400User.exists()) {
                return new org.springframework.security.core.userdetails.User(username, "", new ArrayList<>());
            }
        } catch (AS400SecurityException | ErrorCompletingRequestException | InterruptedException | IOException | ObjectDoesNotExistException e) {
            // Exception not thrown anymore
        }
        throw new UsernameNotFoundException("User " + username + " does not exist");
    }
}
