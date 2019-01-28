package cz.martinheralecky.edu.driving_school.integration_persistent.impl;

import cz.martinheralecky.edu.driving_school.integration_persistent.ConnectionProvider;
import org.osgi.service.component.annotations.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides {@link java.sql.Connection} to MySQL database.
 */
@Component
public class MySQLConnectionProvider implements ConnectionProvider {
    /**
     * The connection instance.
     */
    private Connection connection;

    @Override
    public Connection getConn() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                String.format(
                    "jdbc:%s://%s:%s/%s",
                    System.getenv("DB_DRIVER"),
                    System.getenv("DB_HOST"),
                    System.getenv("DB_PORT"),
                    System.getenv("DB_NAME")
                ),
                System.getenv("DB_USER"),
                System.getenv("DB_PASS"));
        }

        return connection;
    }
}
