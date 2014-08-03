package com.andima.secritaire.config.jpa.drivers;

import com.andima.secritaire.config.jpa.JpaConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@PropertySource("classpath:application.properties")
public class PostgresDriverJpaConfiguration extends JpaConfiguration {
    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
    @Resource
    private Environment environment;

    @Override
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setPassword(environment.getProperty(PROPERTY_NAME_DATABASE_PASSWORD));
        dataSource.setUsername("postgres");

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/secritaireDeGestion");
        return dataSource;
    }
}
