package com.andima.secritaire.config.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class EmbeddedJpaConfiguration extends JpaConfiguration {
    @Override
    public DataSource dataSource() throws SQLException {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        databaseBuilder.setType(EmbeddedDatabaseType.HSQL);
        return databaseBuilder.build();
    }
}
