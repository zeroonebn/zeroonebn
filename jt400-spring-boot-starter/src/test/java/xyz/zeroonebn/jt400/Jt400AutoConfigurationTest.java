package xyz.zeroonebn.jt400;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCDataSource;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

public class Jt400AutoConfigurationTest {

    private AnnotationConfigApplicationContext context;


    @AfterEach
    public void tearDown() {
        if (this.context != null) {
            this.context.close();
        }
    }

    @Test
    public void defaultConfiguraton() {
        load(EmptyConfiguration.class);
        AS400 as400 = context.getBean(AS400.class);

        assertEquals("localhost", as400.getSystemName());
        assertEquals("*CURRENT", as400.getUserId());

        DataSource ds = context.getBean(DataSource.class);
        assertTrue(ds instanceof AS400JDBCDataSource);
        AS400JDBCDataSource as400Ds = (AS400JDBCDataSource) ds;
        assertEquals("localhost", as400Ds.getServerName());
        assertEquals("*CURRENT", as400Ds.getUser());
        assertTrue(as400Ds.isTranslateBinary());
    }

    @Test
    public void nonDefaultConfiguration() {
        load(EmptyConfiguration.class, "jt400.host=ibmi", "jt400.user=user", "jt400.password=password",
                "jt400.translateBinary=false");
        AS400 as400 = context.getBean(AS400.class);

        assertEquals("ibmi", as400.getSystemName());
        assertEquals("USER", as400.getUserId());

        DataSource ds = context.getBean(DataSource.class);
        assertTrue(ds instanceof AS400JDBCDataSource);
        AS400JDBCDataSource as400Ds = (AS400JDBCDataSource) ds;
        assertEquals("ibmi", as400Ds.getServerName());
        assertEquals("USER", as400Ds.getUser());
        assertFalse(as400Ds.isTranslateBinary());
    }


    @Configuration
    static class EmptyConfiguration {
    }

    private void load(Class<?> config, String... environment) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        TestPropertyValues.of(environment).applyTo(applicationContext);
        applicationContext.register(config);
        applicationContext.register(Jt400AutoConfiguration.class);
        applicationContext.refresh();
        this.context = applicationContext;
    }

}
