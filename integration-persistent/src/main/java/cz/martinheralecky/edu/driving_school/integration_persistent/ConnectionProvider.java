package cz.martinheralecky.edu.driving_school.integration_persistent;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides a {@link Connection}.
 */
public interface ConnectionProvider {
    Connection getConn() throws SQLException;
}
