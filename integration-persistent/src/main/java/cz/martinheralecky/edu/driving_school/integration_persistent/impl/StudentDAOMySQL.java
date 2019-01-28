package cz.martinheralecky.edu.driving_school.integration_persistent.impl;

import cz.martinheralecky.edu.driving_school.integration.StudentDAO;
import cz.martinheralecky.edu.driving_school.integration_persistent.ConnectionProvider;
import cz.martinheralecky.edu.driving_school.model.Student;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link StudentDAO} implementation which uses MySQL database to store entities.
 */
@Component
public class StudentDAOMySQL implements StudentDAO {
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
    public List<Student> getAll() throws SQLException {
        var result = new ArrayList<Student>();

        var queryResult = getSelectPs().executeQuery();
        while (queryResult.next()) {
            result.add(new Student(
                new Student.ID(queryResult.getInt(1)),
                queryResult.getString(2),
                queryResult.getString(3),
                queryResult.getString(4),
                queryResult.getString(5),
                queryResult.getDate(6).toLocalDate()));
        }

        return result;
    }

    @Override
    public void delete(Student.ID id) throws SQLException {
        var stmt = getDeletePs();
        stmt.setInt(1, id.getValue());
        stmt.execute();
    }

    private PreparedStatement getInsertPs() throws SQLException {
        if (insertPs == null) {
            insertPs = connProvider.getConn().prepareStatement(
                "INSERT INTO `students` (`firstName`, `surname`, `email`, `phoneNumber`, `birthDate`) " +
                    "VALUES (?, ?, ?, ?, ?)");
        }

        return insertPs;
    }

    private PreparedStatement getSelectPs() throws SQLException {
        if (selectPs == null) {
            selectPs = connProvider.getConn().prepareStatement(
                "SELECT `id`, `firstName`, `surname`, `email`, `phoneNumber`, `birthDate` FROM `students`");
        }

        return selectPs;
    }

    private PreparedStatement getDeletePs() throws SQLException {
        if (deletePs == null) {
            deletePs = connProvider.getConn().prepareStatement("DELETE FROM `students` WHERE `id` = ?");
        }

        return deletePs;
    }
}
