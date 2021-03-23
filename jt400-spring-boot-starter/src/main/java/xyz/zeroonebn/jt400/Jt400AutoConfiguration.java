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
