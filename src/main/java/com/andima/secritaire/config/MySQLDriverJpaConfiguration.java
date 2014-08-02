package com.andima.secritaire.config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by proserve on 02/08/14.
 */
@Configuration
public class MySQLDriverJpaConfiguration extends JpaConfiguration {
    @Override
    public DataSource dataSource() throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setDatabaseName("secritaireDeGestion");
        mysqlDataSource.setPassword("");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setServerName("localhost");
        return mysqlDataSource;
    }
}
