package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    private static final String PROPERTIES_FILE = "h2database.properties";
    private static final String URL_PROPERTY = "db_url";
    private static final String USERNAME_PROPERTY = "user";
    private static final String PASSWORD_PROPERTY = "password";
    private final Properties properties;

    public H2ConnectionFactory() {

        properties = new Properties();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Connection createConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    properties.getProperty(URL_PROPERTY),
                    properties.getProperty(USERNAME_PROPERTY),
                    properties.getProperty(PASSWORD_PROPERTY)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


}


