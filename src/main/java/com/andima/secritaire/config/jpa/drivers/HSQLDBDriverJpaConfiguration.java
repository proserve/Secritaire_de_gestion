package com.andima.secritaire.config.jpa.drivers;

import com.andima.secritaire.config.jpa.JpaConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by proserve on 02/08/14.
 */
@Configuration
public class HSQLDBDriverJpaConfiguration extends JpaConfiguration {
    @Override
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setPassword("");
        dataSource.setUsername("sa");

        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:testDb");
        return dataSource;
    }
}
