package com.digital.bank.util.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DbConnector {
    private final DbProperties dbProperties;

    public DbConnector(DbProperties dbProperties) {
        this.dbProperties = dbProperties;
    }

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                dbProperties.getUrl(),
                dbProperties.getUser(),
                dbProperties.getPassword()
        );
    }
}
