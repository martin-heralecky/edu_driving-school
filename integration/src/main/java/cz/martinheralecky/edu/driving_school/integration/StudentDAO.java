package cz.martinheralecky.edu.driving_school.integration;

import cz.martinheralecky.edu.driving_school.integration.impl.StudentDAOMap;
import cz.martinheralecky.edu.driving_school.model.Student;

import java.time.LocalDate;
import java.util.List;

/**
 * DAO abstract class for student entity. Follows the singleton pattern.
 */
public abstract class StudentDAO implements DAO {
    private static DAOManager daoManager = new DAOManager(StudentDAO.class, StudentDAOMap.class);

    /**
     * Provides an instance of this DAO.
     */
    public static StudentDAO getInstance() {
        return (StudentDAO) daoManager.getInstance();
    }

    /**
     * Stores a new student entity.
     */
    public abstract void create(
        String firstName,
        String surname,
        String email,
        String phoneNumber,
        LocalDate birthDate);

    /**
     * Retrieves all stored student entities.
     */
    public abstract List<Student> getAll();

    /**
     * Deletes a student entity identified by its ID from the storage.
     */
    public abstract void delete(Student.ID id);
}
