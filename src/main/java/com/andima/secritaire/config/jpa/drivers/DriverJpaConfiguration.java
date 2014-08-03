package com.andima.secritaire.config.jpa.drivers;

import com.andima.secritaire.config.jpa.JpaConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by proserve on 02/08/14.
 */
public abstract class DriverJpaConfiguration extends JpaConfiguration {
    @Override
    public abstract DataSource dataSource() throws SQLException;
}
