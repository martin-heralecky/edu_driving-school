package cz.martinheralecky.edu.driving_school.integration_persistent.impl;

import cz.martinheralecky.edu.driving_school.integration.TeacherDAO;
import cz.martinheralecky.edu.driving_school.integration_persistent.ConnectionProvider;
import cz.martinheralecky.edu.driving_school.model.Teacher;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link TeacherDAO} implementation which uses MySQL database to store entities.
 */
@Component
public class TeacherDAOMySQL implements TeacherDAO {
    /**
     * Provider of {@link java.sql.Connection} to the MySQL database.
     */
    @Reference
    ConnectionProvider connProvider;

    private PreparedStatement insertPs;
    private PreparedStatement selectPs;
    private PreparedStatement deletePs;

    @Override
    public void create(String firstName, String surname, String email, String phoneNumber, LocalDate birthDate)
        throws SQLException {
        var stmt = getInsertPs();

        stmt.setString(1, firstName);
        stmt.setString(2, surname);
        stmt.setString(3, email);
        stmt.setString(4, phoneNumber);
        stmt.setDate(5, Date.valueOf(birthDate));

        stmt.execute();
    }

    @Override
    public List<Teacher> getAll() throws SQLException {
        var result = new ArrayList<Teacher>();

        var queryResult = getSelectPs().executeQuery();
        while (queryResult.next()) {
            result.add(new Teacher(
                new Teacher.ID(queryResult.getInt(1)),
                queryResult.getString(2),
                queryResult.getString(3),
                queryResult.getString(4),
                queryResult.getString(5),
                queryResult.getDate(6).toLocalDate()));
        }

        return result;
    }

    @Override
    public void delete(Teacher.ID id) throws SQLException {
        var stmt = getDeletePs();
        stmt.setInt(1, id.getValue());
        stmt.execute();
    }

    /**
     * Returns an INSERT {@link PreparedStatement}. If called for the first time, creates it.
     */
    private PreparedStatement getInsertPs() throws SQLException {
        if (insertPs == null) {
            insertPs = connProvider.getConn().prepareStatement(
                "INSERT INTO `teachers` (`firstName`, `surname`, `email`, `phoneNumber`, `birthDate`) " +
                    "VALUES (?, ?, ?, ?, ?)");
        }

        return insertPs;
    }

    /**
     * Returns a SELECT {@link PreparedStatement}. If called for the first time, creates it.
     */
    private PreparedStatement getSelectPs() throws SQLException {
        if (selectPs == null) {
            selectPs = connProvider.getConn().prepareStatement(
                "SELECT `id`, `firstName`, `surname`, `email`, `phoneNumber`, `birthDate` FROM `teachers`");
        }

        return selectPs;
    }

    /**
     * Returns an DELETE {@link PreparedStatement}. If called for the first time, creates it.
     */
    private PreparedStatement getDeletePs() throws SQLException {
        if (deletePs == null) {
            deletePs = connProvider.getConn().prepareStatement("DELETE FROM `teachers` WHERE `id` = ?");
        }

        return deletePs;
    }
}
