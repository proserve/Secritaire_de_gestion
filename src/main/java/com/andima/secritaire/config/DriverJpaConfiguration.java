package com.andima.secritaire.config;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by proserve on 02/08/14.
 */
public abstract class DriverJpaConfiguration extends JpaConfiguration {
    @Override
    public abstract DataSource dataSource() throws SQLException;
}
