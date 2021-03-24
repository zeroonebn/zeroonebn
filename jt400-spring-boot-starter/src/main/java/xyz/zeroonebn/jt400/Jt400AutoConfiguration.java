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

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Jt400 auto-configuration.
 *
 * @author Damien Ferrand
 */
@Configuration
@ConditionalOnClass(AS400.class)
@EnableConfigurationProperties(Jt400Properties.class)
public class Jt400AutoConfiguration {

    private final Jt400Properties jt400Properties;

    @Bean
    @ConditionalOnMissingBean(AS400.class)
    public AS400 as400() {
        return new AS400(jt400Properties.getHost(), jt400Properties.getUser(), jt400Properties.getPassword());
    }

    @Bean
    @ConditionalOnBean(AS400.class)
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource as400DataSource(AS400 as400) {
        AS400JDBCDataSource retVal = new AS400JDBCDataSource(as400);
        retVal.setTranslateBinary(jt400Properties.isTranslateBinary());
        return retVal;
    }

    public Jt400AutoConfiguration(Jt400Properties jt400Properties) {
        this.jt400Properties = jt400Properties;
    }
}
